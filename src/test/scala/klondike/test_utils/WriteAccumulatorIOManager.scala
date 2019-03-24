package klondike.test_utils

import klondike.io.IOManager

import scala.collection.mutable.ListBuffer

class WriteAccumulatorIOManager extends IOManager {

  private var writes = new ListBuffer[String]()

  override def readInt(title: String): Int = 0

  override def write(message: String): Unit = {
    writes += message
  }

  def asList: List[String] = writes.toList

  def asString: String = asList.mkString
}
