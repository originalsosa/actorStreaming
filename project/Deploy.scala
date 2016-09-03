import deployssh.DeploySSH.Keys._
import deployssh.DeploySSH.{ArtifactSSH, ServerConfig}
import sbt.Keys._
import sbtassembly.AssemblyPlugin.autoImport._

object Deploy {
  lazy val settings = Seq(
    deployConfigs ++= Seq(
      ServerConfig(name="edge_server", host="")
    ),

    deployArtifacts ++= Seq(
      ArtifactSSH((target in (assembly)).value, "/tmp/actorStreaming")
    )
  )
}
