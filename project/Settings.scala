import sbt.Keys._

object Settings extends Dependencies {

  val moduleSettings = Seq(
    organization := "me.rpritchett",
    version := "0.0.1",

    scalaVersion := scalaVersionUsed,

    scalacOptions ++= Seq(
      "-target:jvm-1.7",
      "-encoding", "UTF-8",
      "-unchecked",
      "-deprecation",
      "-feature",
      "-language:existentials",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-language:postfixOps",
      "-Xlint"
    ),

    javacOptions ++= Seq(
      "-source", "1.7",
      "-target", "1.7",
      "Xlint"
    ),

    resolvers ++= commonResolvers,


    libraryDependencies ++= providedDeps map (_ % "provided"),
    libraryDependencies ++= otherDeps,
    libraryDependencies ++= testDeps map (_ % "test"),

    ivyScala := ivyScala.value.map {_.copy(overrideScalaVersion = true)},

    fork in run := true

  )
}
