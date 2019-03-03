package exceptions

case class InvalidMoveException(message: String = "Movement not allowed") extends Exception
