package klondike.models

import klondike.exceptions.EmptyPileException

// A Waste is a Pile with all its cards upturned
class Waste(__cards: List[Card], __name: String = "Waste") extends Pile[Waste] {

  override val name: String = __name
  override val cards: List[Card] = __cards.map(_.upturn())

  def this(pile: Pile[Waste]) = this(pile.cards, pile.name)

  override def pick(): (Card, Waste) = {
    cards match {
      case Nil => throw EmptyPileException(s"$name is empty")
      case _ => (cards.head, new Waste(cards.tail))
    }
  }

  override def put(card: Card): Waste = new Waste(card.upturn() :: cards)

  override def equals(that: Any): Boolean = {
    that match {
      case that: Waste => this.cards == that.cards
      case _ => false
    }
  }
}
