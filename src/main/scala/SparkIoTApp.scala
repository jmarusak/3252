import org.apache.spark.sql.SparkSession

object SparkIoTApp {

  def main(args: Array[String]): Unit = {

    val spark: SparkSession = SparkSession
      .builder()
      .master("local[1]")
      .appName("SparkIoTApp")
      .getOrCreate()

    val df = spark.read.format("json").load("data/iot_devices.json")
    df.show()
  }
}
