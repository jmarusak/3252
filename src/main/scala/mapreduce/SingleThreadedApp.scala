package mapreduce

import mapreduce.utils.Preprocessor.{fileToLines, shardLines}

object SingleThreadedApp {
  def main(args: Array[String]): Unit = {
    val lines: Seq[String] = fileToLines("corpus.txt")
    val shards: Seq[Seq[String]] = shardLines(lines, 4)
  }
}
