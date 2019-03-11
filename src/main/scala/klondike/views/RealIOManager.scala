package klondike.views

object RealIOManager extends IOManager {

  override def readString(title: String): String = {
    print(s"$title: ")
    scala.io.StdIn.readLine()
  }

  override def readInt(title: String): Int = {
    readString(title).toInt
  }

  override def write(string: String): Unit =
    print(string)

  override def write(value: Int): Unit =
    print(value)
}
