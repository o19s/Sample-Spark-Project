import org.apache.spark.{SparkContext, SparkConf}

class SampleSparkJob {
  def main(args: Array[String]) {
    // Bootstrap Spark
    val conf = new SparkConf().setAppName("Sample Spark Scala Application")
    val sc = new SparkContext(conf)

    // Simple Hello World output (performed on the driver
    println("Hello, Scala!")

    // Perform a simple RDD operation
    val rdd = sc.parallelize(Seq("foo", "bar", "baz"))
    val mapped_rdd = rdd.map(term => term.reverse)

    // Performed on the executing nodes (check the output in the Spark UI)
    mapped_rdd.foreach(println)

    // Performed on the driver
    mapped_rdd.take(2).foreach(println)
  }
}
