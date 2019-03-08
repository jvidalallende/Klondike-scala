package klondike.models

// A Foundation is a Pile with all its cards upturned
class Foundation(__cards: List[Card], __name: String = "Foundation") extends Pile(__cards.map(_.upturn()), __name) {

  override def pick(): (Card, Pile) = {
    assertNotEmpty()
    (cards().head, new Foundation(cards().tail))
  }

  override def put(card: Card): Foundation = new Foundation(card.upturn() :: cards())

  def full(): Boolean = {
    cards() match {
      case Nil => false
      case head :: _ => head.isMax
    }
  }
}
