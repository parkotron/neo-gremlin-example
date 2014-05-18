name := "gremlin"

organization := "com.inplaytime"

version := "0.1"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.10" % "1.9.2" % "test",
  "com.tinkerpop.blueprints" % "blueprints-neo4j2-graph" % "2.5.0",
  "com.michaelpollmeier" %% "gremlin-scala" % "2.5.0"
)

