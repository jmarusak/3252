package mapreduce.utils

import scala.io.Source

object Preprocessor {
  def fileToLines(filename: String): Seq[String] =
    Source.fromFile(filename).getLines().toVector

  def shardLines(lines: Seq[String], numShards: Int): Seq[Seq[String]] = {
    val linesSize = lines.size
    val shardSize = if (linesSize % numShards == 0) linesSize / numShards else linesSize / numShards + 1 
    lines.grouped(shardSize).toVector
  }
}
