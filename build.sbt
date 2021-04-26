import com.github.sbt.findbugs.FindbugsPlugin._

name := "play-jongo"

organization := "uk.co.panaxiom"

description := "Play 2.8+ Module for Jongo http://jongo.org/"

version := "2.8.8-jongo1.4-SNAPSHOT"

scalaVersion := "2.13.5"
crossScalaVersions := Seq("2.12.13", "2.13.5")

libraryDependencies ++= Seq(
  "org.mongodb" % "mongo-java-driver" % "3.12.8",
  "org.jongo" % "jongo" % "1.4.1",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.12.3" % "optional",
  "org.assertj" % "assertj-core" % "3.19.0" % Test
)

lazy val root = (project in file(".")).enablePlugins(PlayJava)

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-encoding", "UTF-8", "-Xlint:deprecation")

// "-v" needed for more verbose output, otherwise only the number of tests is reported
Test / testOptions := Seq(Tests.Argument(TestFrameworks.JUnit, "-v"))

// Maven publishing info
publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

Test / publishArtifact := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/alexanderjarvis/play-jongo</url>
  <licenses>
    <license>
      <name>Apache 2.0</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:alexanderjarvis/play-jongo.git</url>
    <connection>scm:git:git@github.com:alexanderjarvis/play-jongo.git</connection>
  </scm>
  <developers>
    <developer>
      <id>alexjarvis</id>
      <name>Alexander Jarvis</name>
      <url>https://github.com/alexanderjarvis</url>
    </developer>
    <developer>
      <id>martin.grotzke</id>
      <name>Martin Grotzke</name>
      <url>https://github.com/magro</url>
    </developer>
    <developer>
      <id>alex.rambau</id>
      <name>Alex Rambau</name>
      <url>https://github.com/arambau</url>
    </developer>
  </developers>)
