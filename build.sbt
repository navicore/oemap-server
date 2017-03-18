name := "microbits"

fork := true
javaOptions in test ++= Seq(
  "-Xms128M", "-Xmx128M",
  "-XX:MaxPermSize=128M",
  "-XX:+CMSClassUnloadingEnabled"
)

parallelExecution in test := false

version := "1.0"

scalaVersion := "2.11.8"

val AkkaVersion       = "2.3.9"
val AkkaHttpVersion   = "2.0.1"
val Json4sVersion     = "3.2.11"

libraryDependencies := 
    Seq(

      "ch.qos.logback" % "logback-classic" % "1.1.7",
      "com.typesafe" % "config" % "1.2.1",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",

      "com.typesafe.akka" %% "akka-slf4j"             % AkkaVersion,
      "com.typesafe.akka" %% "akka-http-experimental" % AkkaHttpVersion,
      "org.json4s"        %% "json4s-native"          % Json4sVersion,
      "org.json4s"        %% "json4s-ext"             % Json4sVersion,
      "de.heikoseeberger" %% "akka-http-json4s"       % "1.4.2",

      "org.scalatest" %% "scalatest" % "3.0.1" % "test"
    )

// include 'provided' libs when running local
run in Compile <<= Defaults.runTask(fullClasspath in Compile, mainClass in(Compile, run), runner in(Compile, run))
runMain in Compile <<= Defaults.runMainTask(fullClasspath in Compile, runner in(Compile, run))

test in assembly := {}
mainClass in assembly := Some("onextent.oemap.server.Main")
assemblyJarName in assembly := "oemap-server.jar"

assemblyMergeStrategy in assembly := {
  case x if x.endsWith("io.netty.versions.properties") => MergeStrategy.first
  case PathList("com",   "google", _*) => MergeStrategy.last
  case PathList("com",   "esotericsoftware", _*) => MergeStrategy.last
  case PathList("io",    "netty", _*) => MergeStrategy.last
  case PathList("org",   "slf4j", _*) => MergeStrategy.last
  case PathList("org",   "apache", _*) => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

// scala lint
lazy val compileScalastyle = taskKey[Unit]("compileScalastyle")
compileScalastyle := org.scalastyle.sbt.ScalastylePlugin.scalastyle.in(Compile).toTask("").value

(compile in Compile) <<= (compile in Compile) dependsOn compileScalastyle

lazy val testScalastyle = taskKey[Unit]("testScalastyle")
testScalastyle := org.scalastyle.sbt.ScalastylePlugin.scalastyle.in(Test).toTask("").value


