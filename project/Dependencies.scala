import sbt._
import Library._

object Version {
  final val Scala     = "2.10.4"
  final val ScalaTest = "2.2.6"
  final val Akka      = "2.2.3-shaded-protobuf"
  final val AkkaOrg   = "org.spark-project.akka"
  final val Spark     = "1.5.0-cdh5.5.2"
  final val SparkTesting = Spark + "_0.3.3"
  final val Hadoop    = "2.6.0-cdh5.5.2"
  final val TypesafeConfig = "1.2.1"
  final val Logback   = "1.1.7"
  final val Slf4J     = "1.7.21"
  final val Aws4s     = "1.0.11"
}

object Library {


  val scalactic         =     "org.scalactic" %% "scalactic" % Version.ScalaTest
  val scalaTest         =     "org.scalatest" %% "scalatest" % Version.ScalaTest

  val akkaActor         =     Version.AkkaOrg %% "akka-actor" % Version.Akka
  val akkaSlf4j         =     Version.AkkaOrg %% "akka-slf4j" % Version.Akka
  val akkaRemote        =     Version.AkkaOrg %% "akka-remote" % Version.Akka

  val sparkCore         =     "org.apache.spark" %% "spark-core" % Version.Spark
  val sparkStreaming    =     "org.apache.spark" %% "spark-streaming" % Version.Spark
  val sparkSql          =     "org.apache.spark" %% "spark-sql" % Version.Spark
  val sparkHive         =     "org.apache.spark" %% "spark-hive" % Version.Spark
  val sparkTesting      =     "com.holdenkarau" %% "spark-testing-base" % "1.5.0_0.3.3"//Version.SparkTesting

  val hadoopYarnAPI     =     "org.apache.hadoop" % "hadoop-yarn-api" % Version.Hadoop
  val hadoopYarnClient  =     "org.apache.hadoop" % "hadoop-yarn-client" % Version.Hadoop
  val hadoopYarnCommon  =     "org.apache.hadoop" % "hadoop-yarn-common" % Version.Hadoop
  val hadoopYarnApps    =     "org.apache.hadoop" % "hadoop-yarn-applications-distributedshell" % Version.Hadoop
  val hadoopYarnServer  =     "org.apache.hadoop" % "hadoop-yarn-server-web-proxy" % Version.Hadoop
  val hadoopClient      =     "org.apache.hadoop" % "hadoop-client" % Version.Hadoop

  val slf4j             =     "org.slf4j" % "slf4j-api" % Version.Slf4J
  val logback           =     "ch.qos.logback" % "logback-classic" % Version.Logback

  val typesafeConfig    =     "com.typesafe" % "config" % Version.TypesafeConfig

  val aws4s             =     "org.sisioh" %% "aws4s" % Version.Aws4s
}

object Resolvers {

  val resolvers = Seq(
    Resolver sonatypeRepo "public",
    Resolver typesafeRepo "releases",
    Resolver bintrayRepo("hseeberger", "maven"),
    Resolver bintrayRepo("lonelyplanet", "maven"))
}

trait Dependencies {

  val scalaVersionUsed = Version.Scala

  val commonResolvers = Resolvers.resolvers

  val providedDeps = Seq(
    akkaActor, akkaSlf4j, akkaRemote, typesafeConfig, sparkCore, sparkStreaming, sparkSql, sparkHive,
    hadoopClient, hadoopYarnAPI, hadoopYarnClient, hadoopYarnCommon, hadoopYarnApps, hadoopYarnServer
  )

  val otherDeps = Seq(aws4s)

  val testDeps = Seq(scalaTest, scalactic, sparkTesting)
}