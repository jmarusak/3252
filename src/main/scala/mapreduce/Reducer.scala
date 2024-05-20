package mapreduce

object Reducer {
  def shuffle(data: List[(String, Int)]): List[(String, List[Int])] = {
    data.groupBy(_._1).mapValues(_.map(_._2)).toList
  }

  def reduce(data: List[(String, List[Int])]): List[(String, Int)] = {
    data.map { case (key, values) => key -> values.sum }.toList
  }
}
