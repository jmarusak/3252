import scala.io.Source

import mapreduce.{Splitter, Mapper, Reducer}

object SingleThreadedApp {
  def load(filename: String): List[String] =
    Source.fromFile(filename).getLines().toList

  def main(args: Array[String]): Unit = {
    val corpus: List[String] = load("corpus.txt")

    var mapped = List.empty[(String, Int)]
    val corpusPartitions: List[List[String]] = Splitter.split(corpus, 4)
    for (partition <- corpusPartitions) {
      val words: List[String] = Mapper.tokenize(partition)
      val mapping: List[(String, Int)] = Mapper.map(words)
      mapped ++= mapping 
    }

    val shuffled: List[(String, List[Int])] = Reducer.shuffle(mapped)

    var reduced = List.empty[(String, Int)]
    var shufflePartitions: List[List[(String, List[Int])]] = Splitter.split(shuffled, 4)
    for (partition <- shufflePartitions) {
      val reducedSplit: List[(String, Int)] = Reducer.reduce(partition)
      reduced ++= reducedSplit
    }
 
    val sorted = reduced.sortBy(_._2).reverse
    sorted.foreach(println)
  }
}
