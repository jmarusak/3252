package mapreduce

object Mapper {
  def tokenize(data: List[String]): List[String] = {
    data.flatMap(_.split("\\s+").map(_.toLowerCase))
  }

  def map(data: List[String]): List[(String, Int)] = {
    data.groupBy(identity).mapValues(_.size).toList
  }
}
