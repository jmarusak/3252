package mapreduce

import munit._
import mapreduce.Mapper._

class MapperSuite extends FunSuite {

  test("Mapper.tokenize() should return a list of words") {
    val data = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val expected = List(List(1, 2, 3, 4), List(5, 6, 7, 8), List(9, 10))
    val obtained = Mapper.split(data, 3)
    assertEquals(obtained, expected)
  }
}
