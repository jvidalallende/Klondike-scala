package models

// A Deck is a Pile with all its cards downturned
class Deck(__cards: List[Card]) extends Pile(__cards.map(_.downturn())) {

  override def pick(): (Card, Pile) = {
    assertNotEmpty()
    (cards().head, new Deck(cards().tail))
  }

  override def put(card: Card): Deck = new Deck(card.downturn() :: cards())
}
