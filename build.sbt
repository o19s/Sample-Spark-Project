name := "sample-spark-project"
version := "1.0"
scalaVersion := "2.10.5"

val sparkVersion = "1.4.1"

// Note the dependencies are provided
libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion % "provided"
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion % "provided"

// Do not include Scala in the assembled JAR
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

// META-INF discarding for the FAT JAR
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

lazy val spark_run = taskKey[Unit]("Builds the assembly and ships it to the Spark Cluster")
spark_run := {
  ("/full/path/to/bin/spark_submit " + assembly.value) !
}