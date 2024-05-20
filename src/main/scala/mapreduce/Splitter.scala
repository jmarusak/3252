package mapreduce

object Splitter {
  def split[A](data: List[A], partitions: Int): List[List[A]] = {
    val size = data.size
    val partitionSize = if (size % partitions == 0) size / partitions else size / partitions + 1 
    data.grouped(partitionSize).toList
  }
}
