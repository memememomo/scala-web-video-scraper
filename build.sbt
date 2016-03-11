name := "web-video-scraper"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.apache.httpcomponents" % "httpclient" % "4.5",
  "org.slf4j" % "slf4j-simple" % "1.7.7",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test"
)
