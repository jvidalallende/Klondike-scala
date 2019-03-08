package klondike.views

abstract class IOManager {

  def readString(title: String): String

  def readInt(title: String): Int

  def write(string: String): Unit

  def write(value: Int): Unit
}
