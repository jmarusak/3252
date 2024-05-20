import org.scalatest._

import mapreduce.Splitter._

class SplitterSpec extends FlatSpec with Matchers {

  "Splitter.split" should { 

  "correctly split a list into partitions" in {
    val testData = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val expectedPartitions = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9), List(10))

    split(testData, 3) shouldEqual expectedPartitions
  }

  "handle an empty list" in {
    val testData = List.empty[Int]

    split(testData, 5) shouldEqual List.empty[List[Int]]
  }

  "handle fewer partitions than elements in the list" in {
    val testData = List(1, 2, 3, 4, 5)
    val expectedPartitions = List(List(1), List(2), List(3), List(4), List(5))

    split(testData, 10) shouldEqual expectedPartitions
  }

  "handle more partitions than elements in the list" in {
    val testData = List(1, 2, 3)
    val expectedPartitions = List(List(1), List(2), List(3))

    split(testData, 5) shouldEqual expectedPartitions
  }
}
}
