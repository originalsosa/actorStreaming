import sbt._
import Keys._
import sbtassembly.AssemblyPlugin.autoImport._

object Assembly {
  lazy val settings = Seq(
    assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false),

    assemblyJarName in assembly := "actorStreaming.jar",
    test in assembly := {},
    assemblyMergeStrategy in assembly := {
      case m if m.toLowerCase.endsWith("manifest.mf") => MergeStrategy.discard
      case m if m.toLowerCase.matches("meta-inf.*\\.sf$") => MergeStrategy.discard
      case "reference.conf" => MergeStrategy.concat
      case _ => MergeStrategy.first
    }
  )
}