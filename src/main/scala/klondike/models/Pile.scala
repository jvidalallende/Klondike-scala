package klondike.models

import klondike.exceptions.EmptyPileException

abstract class Pile {

  val name: String
  val cards: List[Card]

  def pick(): (Card, Pile)

  def put(card: Card): Pile

  def empty: Boolean = {
    cards match {
      case Nil => true
      case _ => false
    }
  }
}
