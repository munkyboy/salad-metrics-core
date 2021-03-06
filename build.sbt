import scala.util.Properties
import com.typesafe.sbt.SbtScalariform.scalariformSettings

name := "salad-metrics-core"

organization := "com.netaporter.salad"

version := "0.2.9-SNAPSHOT"

scalaVersion := "2.11.2"

scalacOptions ++= Seq("-feature", "-deprecation")

crossScalaVersions := Seq("2.10.3", "2.11.2")

val akka = "2.3.4"
val spray = "1.3.1"
val jackson = "2.2.2"
val metrics = "3.0.1"

libraryDependencies ++= Seq(
    "com.codahale.metrics" % "metrics-core" % metrics,
    "com.codahale.metrics" % "metrics-json" % metrics,
    "com.fasterxml.jackson.core" % "jackson-databind" % jackson,
    "com.fasterxml.jackson.module" % "jackson-module-afterburner" % jackson,
    "com.twitter" % "jsr166e" % "1.1.0",
    "com.typesafe.akka" %% "akka-actor" % akka,
    "com.typesafe.akka" %% "akka-testkit" % akka % "test",
    "io.spray" %% "spray-caching" % spray,
    "io.spray" %% "spray-routing" % spray,
    "io.spray" %% "spray-testkit" % spray % "test",
    "org.scalatest" %% "scalatest" % "2.2.1" % "test",
    "org.specs2" %% "specs2-core" % "2.3.13" % "test"
)

scalariformSettings

// Generate junit xml reports
testOptions <+= (target in Test) map { t =>
  Tests.Argument("-o", "-u", t + "/test-reports")
}

publishTo := Some(Resolver.file("file", new File("target/publish")))

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomExtra := (
  <url>https://github.com/net-a-porter/salad-metrics-core</url>
    <licenses>
      <license>
        <name>Apache 2</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:net-a-porter/salad-metrics-core.git</url>
      <connection>scm:git@github.com:net-a-porter/salad-metrics-core.git</connection>
    </scm>
    <developers>
      <developer>
        <id>dom</id>
        <name>Dominic Tootell</name>
      </developer>
      <developer>
        <id>theon</id>
        <name>Ian Forsey</name>
        <url>http://theon.github.io</url>
      </developer>
    </developers>)
