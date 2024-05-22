package mapreduce

import munit._
import mapreduce.Splitter._

class SplitterSuite extends FunSuite {

  test("Splitter.split() correctly split a list into partitions") {
    val data = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val expected = List(List(1, 2, 3, 4), List(5, 6, 7, 8), List(9, 10))
    val obtained = Splitter.split(data, 3)
    assertEquals(obtained, expected)
  }
}
