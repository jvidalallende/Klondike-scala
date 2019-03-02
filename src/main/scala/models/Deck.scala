package models

class Deck(cards: List[Card]) extends Pile(cards) {

  override def pick(): (Card, Pile) = {
    assertNotEmpty()
    (cards.head, new Deck(cards.tail))
  }

  override def put(card: Card): Deck = new Deck(card.downturn() :: cards)
}
