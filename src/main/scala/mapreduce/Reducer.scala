package mapreduce

object Reducer {
  /**
    * Combine the data by grouping the values of the same key.
    *
    * @param data List of tuples of key and value.
    * @return List of tuples of key and list of values.
    */
  def shuffle(data: List[(String, Int)]): List[(String, List[Int])] = {
    data.groupBy(_._1).mapValues(_.map(_._2)).toList
  }

  /**
    * Reduce the values of the same key by summing them up.
    *
    * @param data List of tuples of key and list of values.
    * @return List of tuples of key and sum of values.
    */
  def reduce(data: List[(String, List[Int])]): List[(String, Int)] = {
    data.map { case (key, values) => key -> values.sum }.toList
  }
}
