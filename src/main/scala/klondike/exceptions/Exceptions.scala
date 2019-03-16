package klondike.exceptions

case class InvalidValueException(message: String) extends Exception(message)
case class EmptyPileException(message: String) extends Exception(message)
case class InvalidMoveException(message: String = "Movement not allowed") extends Exception(message)
case class ExitGameException(message: String = "Good bye!") extends Exception(message)
