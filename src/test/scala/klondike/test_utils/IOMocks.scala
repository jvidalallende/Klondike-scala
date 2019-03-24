package klondike.test_utils

import klondike.views.IOManager
import org.scalamock.scalatest.MockFactory

object IOMocks extends MockFactory {

  def readInt(values: Seq[Int]): IOManager = {
    val mockIO = mock[IOManager]
    inSequence {
      for (value <- values) {
        (mockIO.readInt _).expects(*).returning(value).noMoreThanOnce()
      }
    }
    mockIO
  }

  def readIntAllowingWrites(values: Seq[Int]): IOManager = {
    val mockIO = readInt(values)
    (mockIO.write(_: String)).expects(*).anyNumberOfTimes()
    mockIO
  }
}
