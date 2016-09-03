package actorstreaming

import akka.actor.{Actor, ActorRef, ActorSystem, Address, Props}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.receiver.ActorHelper

import scala.collection.mutable
import scala.util.Random

import java.net.InetAddress

case class SubscribeReceiver(receiverActor: ActorRef)

case class UnsubscribeReceiver(receiverActor: ActorRef)

class FeederActor extends Actor {
  val rand = new Random()
  var receivers: mutable.LinkedList[ActorRef] = new mutable.LinkedList[ActorRef]()
  val strings: Array[String] = Array("words", "may", "count")

  def makeMessage(): String = {
    val x = rand.nextInt(3)
    strings(x) + strings(2 - x)
  }

  new Thread() {
    override def run() {
      while (true)
        Thread.sleep(500)
      println(s"Number of Receivers ${receivers.size}")
      receivers.foreach(_ ! makeMessage)
    }
  }.start ()

  def receive: Receive = {
    case SubscribeReceiver (receiverActor: ActorRef) =>
      receivers = mutable.LinkedList (receiverActor) ++ receivers
    case UnsubscribeReceiver (receiverActor: Actor) =>
      receivers = receivers.dropWhile (x => x.eq (receiverActor) )
  }
}

class SampleActorReceiver(url: String) extends Actor with ActorHelper {
  lazy private val remotePublisher = context.actorSelection(url)

  override def preStart() {
    remotePublisher ! SubscribeReceiver(context.self)
  }

  override def postStop() {
    remotePublisher ! UnsubscribeReceiver(context.self)
  }

  def receive: Receive = {
    case msg =>
      store(msg.asInstanceOf[String])
  }
}

object SampleActorReceiver {
  def props(url: String): Props = Props(new SampleActorReceiver(url))
}

object ActorStreaming extends App {

  val system = ActorSystem("ActorStreaming")

  val feederRef = system.actorOf(Props[FeederActor], "FeederActor")

  val sparkConf = new SparkConf().setAppName("Actor Streaming")
  val ssc = new StreamingContext(sparkConf, Seconds(2))

  val url: String = feederRef.path.toStringWithAddress(Address("akka.tcp", system.name, InetAddress.getLocalHost.getHostAddress, 2552))

  val lines = ssc.actorStream[String](SampleActorReceiver.props(url), "SampleReceiver")
  lines.flatMap(_.split("\\s+")).map(x => (x, 1)).reduceByKey(_ + _).print()

  ssc.start()

  ssc.awaitTermination()

  system.awaitTermination()
}