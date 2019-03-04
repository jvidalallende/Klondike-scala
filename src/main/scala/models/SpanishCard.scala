package models

import exceptions.InvalidValueException

class SpanishCard(__value: Int, __suit: String, __upturned: Boolean = false)
  extends Card(SpanishCard.filterValue(__value), SpanishCard.filterSuit(__suit), __upturned) {

  override def upturn(): Card = new SpanishCard(value(), suit(), true)

  override def downturn(): Card = new SpanishCard(value(), suit(), false)

  override def isMin(): Boolean = {
    value == SpanishCard.MIN_VALUE
  }

  override def isMax(): Boolean = {
    value == SpanishCard.MAX_VALUE
  }
}

object SpanishCard {
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
