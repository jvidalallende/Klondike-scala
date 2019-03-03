package models

class Deck(__cards: List[Card]) extends Pile(__cards) {

  override def pick(): (Card, Pile) = {
    assertNotEmpty()
    (cards().head, new Deck(cards().tail))
  }

  override def put(card: Card): Deck = new Deck(card.downturn() :: cards())
}
