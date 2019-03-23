package klondike.test_utils

import klondike.views.IOManager
import org.scalamock.scalatest.MockFactory

object IOStubs extends MockFactory {

  def readInt(values: Seq[Int]) : IOManager = {
    val stubIO = stub[IOManager]
    inSequence {
      for (value <- values) {
        (stubIO.readInt _).when(*).returns(value).noMoreThanOnce()
      }
    }
   stubIO
  }
}
