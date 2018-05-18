resolvers += "jitpack" at "https://jitpack.io"

name := "test-cherry"

version := "0.0.1"

scalaVersion := "2.11.12"

sparkVersion := "2.3.0"

sparkComponents ++= Seq("sql")

libraryDependencies += "com.github.quocanh" % "spark-cherry" % "v2.11-0.0.2"
libraryDependencies += "com.github.mrpowers" % "spark-fast-tests" % "v2.3.0_0.7.0" % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

// test suite settings
fork in Test := true
javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:+CMSClassUnloadingEnabled")
// Show runtime of tests
testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oD")

// JAR file settings
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
