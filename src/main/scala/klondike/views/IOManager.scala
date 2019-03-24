package klondike.views

abstract class IOManager {

  def readInt(title: String): Int

  def write(string: String): Unit
}
