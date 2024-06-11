import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, desc, avg}

object SparkIoTApp {

  def main(args: Array[String]): Unit = {

    val spark: SparkSession = SparkSession
      .builder()
      .master("local[1]")
      .appName("SparkIoTApp")
      .getOrCreate()

    val df = spark.read.format("json").load("data/iot_devices.json")

    val deviceCount = df.where(col("device_name").contains("sensor-pad") and col("cca3") === "POL").count
    println(s"Answer 2.1: There is $deviceCount reported from Poland")

    val colorCount = df.groupBy(col("lcd")).count.count
    println(s"Answer 2.2: There is $colorCount distinct colors in the dataset")

    val df2 = df.where(col("device_name").contains("device-mac")).groupBy(col("cn")).count.sort(desc("count")).limit(5)
    println("Answer 2.3: 5 countries with the largest number of MAC devices ")
    print(df2.show)

    val df3 = df.groupBy(col("cn")).agg(avg(col("temp")).alias("avg_temp")).orderBy(desc("avg_temp")).limit(10)
    println("Answer 2.4: 10 countries with the higest average temperature ")
    print(df3.show(truncate=false))

    spark.stop()
  }
}
