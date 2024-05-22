package mapreduce

object Splitter {
  /**
   * Splits the data into the given number of partitions.
   * 
   * @param data The data to split.
   * @param partitions The number of partitions to split the data into.
   * @return A list of lists where each list is a partition of the data.
   */
  def split[A](data: List[A], partitions: Int): List[List[A]] = {
    val size = data.size
    val partitionSize = if (size % partitions == 0) size / partitions else size / partitions + 1 
    data.grouped(partitionSize).toList
  }
}
