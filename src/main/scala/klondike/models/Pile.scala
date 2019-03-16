package klondike.models

import klondike.exceptions.EmptyPileException

trait Pile[A] {

  val name: String
  val cards: List[Card]

  def pick(): (Card, Pile[A])

  def put(card: Card): Pile[A]

  def empty: Boolean = {
    cards match {
      case Nil => true
      case _ => false
    }
  }
}
