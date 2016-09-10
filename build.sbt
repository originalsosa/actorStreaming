lazy val actorStreaming = project
  .copy(id="actorStreaming")
  .in(file("."))
  .enablePlugins(DeploySSH)
  .settings(Settings.moduleSettings:_*)
  .settings(Assembly.settings:_*)
  .settings(Deploy.settings:_*)

resolvers += "Cloudera's CDH5 Maven repo" at "https://repository.cloudera.com/artifactory/cloudera-repos/"