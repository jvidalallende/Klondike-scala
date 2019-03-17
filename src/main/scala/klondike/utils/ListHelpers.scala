package klondike.utils

import klondike.exceptions.InvalidValueException

object ListHelpers {

  def replaceAt[A](originalList: List[A], index: Int, replacement: A): List[A] = {

    index match {
      case 0 if originalList.nonEmpty => replacement :: originalList.tail

      case n if 0 < n && n < originalList.length =>
        val prefix = originalList.take(n)
        val suffix = originalList.takeRight(originalList.length - (n + 1))
        prefix ::: replacement :: suffix

      case _ => throw InvalidValueException(s"Index [$index] is not valid for list ${originalList.toString}")
    }
  }
}
