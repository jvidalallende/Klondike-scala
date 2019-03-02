package models

class Waste(cards: List[Card]) extends Pile(cards) {

  override def pick(): (Card, Pile) = {
    assertNotEmpty()
    (cards.head, new Waste(cards.tail))
  }

  override def put(card: Card): Waste = new Waste(card.upturn() :: cards)
}
