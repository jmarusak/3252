package mapreduce

object Mapper {
  /**
   * Tokenize the text and map it to a list of words.
   *
   * @param data The data to be tokenized.
   * @return A list of words in text.
   */
  def tokenize(data: List[String]): List[String] = {
    data.flatMap(_.split("\\s+").map(_.toLowerCase))
  }

  /**
   * Map the words to a list of key-value pairs.
   *
   * @param data The workds to be mapped.
   * @return A list of key-value pairs (word, count).
   */
  def map(data: List[String]): List[(String, Int)] = {
    data.groupBy(identity).mapValues(_.size).toList
  }
}
