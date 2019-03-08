package klondike.exceptions

case class InvalidValueException(message: String) extends Exception
case class EmptyPileException(message: String) extends Exception
case class InvalidMoveException(message: String = "Movement not allowed") extends Exception
