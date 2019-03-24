package klondike.io

abstract class IOManager {

  def readInt(title: String): Int

  def write(string: String): Unit
}
