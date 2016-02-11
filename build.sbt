name := """play-java"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "be.objectify"  %% "deadbolt-java"     % "2.4.4",
  "com.feth"      %% "play-authenticate" % "0.7.1",
  "org.mongodb" % "mongo-java-driver" % "3.2.1",
  "org.mongodb.morphia" % "morphia" % "1.0.1",
  "org.mongodb.morphia" % "morphia-logging-slf4j" % "1.0.1",
  "org.mongodb.morphia" % "morphia-validation" % "1.0.1",
  "org.webjars" % "bootstrap" % "3.0.0",
  javaJdbc,
  cache,
  javaWs
)

resolvers ++= Seq(
  "Apache" at "http://repo1.maven.org/maven2/",
  "jBCrypt Repository" at "http://repo1.maven.org/maven2/org/",
  Resolver.url("Objectify Play Repository", url("http://schaloner.github.io/releases/"))(Resolver.ivyStylePatterns),
  "play-authenticate (release)" at "http://joscha.github.io/play-authenticate/repo/releases/",
  "play-authenticate (snapshot)" at "http://joscha.github.io/play-authenticate/repo/snapshots/"
)
