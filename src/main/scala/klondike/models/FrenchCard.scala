package klondike.models

import klondike.exceptions.InvalidValueException

class FrenchCard(__value: Int, __suit: String, __upturned: Boolean = false)
  extends Card(FrenchCard.filterValue(__value), FrenchCard.filterSuit(__suit), __upturned) {

  override def upturn(): Card = new FrenchCard(value, suit, true)

  override def downturn(): Card = new FrenchCard(value, suit, false)

  override def isMin: Boolean = {
    value == FrenchCard.MIN_VALUE
  }

  override def isMax: Boolean = {
    value == FrenchCard.MAX_VALUE
  }
}


object FrenchCard {
  val MIN_VALUE: Int = 1
  val MAX_VALUE: Int = 13

  val suits = List("hearts", "diamonds", "spades", "clubs")

  def filterValue(value: Int): Int = {
    value match {
      case _ if MIN_VALUE <= value && value <= MAX_VALUE => value
      case _ => throw InvalidValueException(s"Value [$value] not allowed for FrenchCard")
    }
  }

  def filterSuit(suit: String): String = {
    suit match {
      case _ if suits.contains(suit) => suit
      case _ => throw InvalidValueException(s"Suit [$suit] not allowed for FrenchCard")
    }
  }
}
