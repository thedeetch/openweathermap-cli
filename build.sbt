name := "openweathermap-cli"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-core" % "2.4.7",
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "2.4.7",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test"
)
