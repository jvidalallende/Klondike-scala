package klondike.models

import klondike.exceptions.EmptyPileException

// A Foundation is a Pile with all its cards upturned
class Foundation(__cards: List[Card], __name: String = "Foundation") extends Pile {

  override val name: String = __name
  override val cards: List[Card] = __cards.map(_.upturn())

  def this(pile: Pile) = this(pile.cards, pile.name)

  override def pick(): (Card, Foundation) = {
    cards match {
      case Nil => throw EmptyPileException(s"$name is empty")
      case _ => (cards.head, new Foundation(cards.tail))
    }
  }

  override def put(card: Card): Foundation = new Foundation(card.upturn() :: cards)

  override def equals(that: Any): Boolean = {
    that match {
      case that: Foundation => this.cards == that.cards
      case _ => false
    }
  }

  def full(): Boolean = {
    cards match {
      case Nil => false
      case head :: _ => head.isMax
    }
  }
}
