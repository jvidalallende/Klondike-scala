package klondike.models

import klondike.exceptions.EmptyPileException

// A Deck is a Pile with all its cards downturned
class Deck(__cards: List[Card], __name: String = "Deck") extends Pile[Deck] {

  override val name: String = __name
  override val cards: List[Card] = __cards.map(_.downturn())

  override def pick(): (Card, Deck) = {
    cards match {
      case Nil => throw EmptyPileException(s"$name is empty")
      case _ => (cards.head, new Deck(cards.tail))
    }
  }

  override def put(card: Card): Deck = new Deck(card.downturn() :: cards)

  override def equals(that: Any): Boolean = {
    that match {
      case that: Deck => this.cards == that.cards
      case _ => false
    }
  }
}
