lazy val actorStreaming = project
  .copy(id="actorStreaming")
  .in(file("."))
  .enablePlugins(DeploySSH)
  .settings(Settings.moduleSettings:_*)
  .settings(Assembly.settings:_*)
  .settings(Deploy.settings:_*)