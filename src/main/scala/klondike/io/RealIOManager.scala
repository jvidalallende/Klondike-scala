package klondike.io

object RealIOManager extends IOManager {

  override def readInt(title: String): Int = {
    print(s"$title: ")
    scala.io.StdIn.readLine().toInt
  }

  override def write(string: String): Unit =
    print(string)
}
