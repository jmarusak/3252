import scala.io.Source
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import mapreduce.{Splitter, Mapper, Reducer}

object MultiThreadedApp {
  def load(filename: String): List[String] =
    Source.fromFile(filename).getLines().toList

  def printNow(): Unit = {
    val now = java.time.LocalTime.now()
    println(s"[$now] Thread: ${Thread.currentThread().getName}")
  }

  def main2(args: Array[String]): Unit = {
    val corpus: List[String] = load("corpus.txt")

    var mapped = List.empty[(String, Int)]
    val corpusPartitions: List[List[String]] = Splitter.split(corpus, 4)

    val startTime = System.currentTimeMillis()
    val futures = corpusPartitions.map { partition =>
      Future {
        Thread.sleep(1000)
        val words: List[String] = Mapper.tokenize(partition)
        val mapping: List[(String, Int)] = Mapper.map(words)
        printNow()
        mapping
      }
    }

    for {
      future <- futures
      mapping <- future
    } mapped ++= mapping

    println(mapped)

    /*
    for (partition <- corpusPartitions) {
      val words: List[String] = Mapper.tokenize(partition)
      val mapping: List[(String, Int)] = Mapper.map(words)
      mapped ++= mapping 
    }
    */

   println(s"completed in ${System.currentTimeMillis() - startTime} ms")
  }
}
