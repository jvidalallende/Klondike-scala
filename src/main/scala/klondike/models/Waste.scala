package klondike.models

// A Waste is a Pile with all its cards upturned
class Waste(__cards: List[Card], __name: String = "Waste") extends Pile(__cards.map(_.upturn()), __name) {

  override def pick(): (Card, Pile) = {
    assertNotEmpty()
    (cards().head, new Waste(cards().tail))
  }

  override def put(card: Card): Waste = new Waste(card.upturn() :: cards())
}
