package klondike.views

import klondike.models._

abstract class BoardBuilder {

  protected def createCards(): List[Card]

  def build(numberOfTableauPiles: Int): Board = {
    val (tableauPiles, deckCards) = createTableauPiles(numberOfTableauPiles, createCards())
    val emptyFoundations = List(Nil, Nil, Nil, Nil).map(x => new Foundation(x))
    new Board(new Deck(deckCards), new Waste(Nil), emptyFoundations, tableauPiles)
  }

  protected def createTableauPiles(numberOfTableauPiles: Int, cards: List[Card]): (List[TableauPile], List[Card]) = {
    val (downturnedTableauPiles, remainingCards) = distributeCardsInTableauPiles(numberOfTableauPiles, Nil, cards)
    val tableauPiles = downturnedTableauPiles.map(tp => new TableauPile(tp.cards.head.upturn() :: tp.cards.tail))
    (tableauPiles, remainingCards)
  }

  protected def distributeCardsInTableauPiles(numberOfTableauPiles: Int, tableauPiles: List[TableauPile], cards
  : List[Card]): (List[TableauPile], List[Card]) = {
    tableauPiles.length match {
      case length if length == numberOfTableauPiles => (tableauPiles, cards)
      case length =>
        val newTableauPiles = tableauPiles ::: new TableauPile(cards.take(length + 1)) :: Nil
        val remainingCards = cards.takeRight(cards.length - (length + 1))
        distributeCardsInTableauPiles(numberOfTableauPiles, newTableauPiles, remainingCards)
    }
  }

}
