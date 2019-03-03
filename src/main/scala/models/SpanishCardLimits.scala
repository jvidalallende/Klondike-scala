package models

import exceptions.InvalidValueException

object SpanishCardLimits {
  val MIN_VALUE: Int = 1
  val MAX_VALUE: Int = 10

  val suits = List("golds", "cups", "swords", "clubs")

  def filterValue(value: Int): Int = {
    value match {
      case _ if MIN_VALUE <= value && value <= MAX_VALUE => value
      case _ => throw InvalidValueException(s"Value [$value] not allowed for SpanishCard")
    }
  }

  def filterSuit(suit: String): String = {
    suit match {
      case _ if suits.contains(suit) => suit
      case _ => throw InvalidValueException(s"Suit [$suit] not allowed for SpanishCard")
    }
  }
}
