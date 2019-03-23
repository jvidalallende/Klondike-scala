package klondike.views

import klondike.models._

import scala.util.Random

object SpanishBoardBuilder {

  def build(numberOfTableauPiles: Int): Board = {
    val (tableauPiles, deckCards) = createTableauPiles(numberOfTableauPiles, createCards())
    val emptyFoundations = List(Nil, Nil, Nil, Nil).map(x => new Foundation(x))
    new Board(new Deck(deckCards), new Waste(Nil), emptyFoundations, tableauPiles)
  }

  private def createCards(): List[SpanishCard] = {
    val cardSequence = for (value <- SpanishCard.MIN_VALUE to SpanishCard.MAX_VALUE; suit <- SpanishCard.suits) yield new SpanishCard(value, suit)
    Random.shuffle(cardSequence).toList
  }

  private def createTableauPiles(numberOfTableauPiles: Int, cards: List[SpanishCard]): (List[TableauPile], List[SpanishCard]) = {
    val (downturnedTableauPiles, remainingCards) = distributeCardsInTableauPiles(numberOfTableauPiles, Nil, cards)
    val tableauPiles = downturnedTableauPiles.map(tp => new TableauPile(tp.cards.head.upturn() :: tp.cards.tail))
    (tableauPiles, remainingCards)
  }

  private def distributeCardsInTableauPiles(numberOfTableauPiles: Int, tableauPiles: List[TableauPile], cards
  : List[SpanishCard]): (List[TableauPile], List[SpanishCard]) = {
    tableauPiles.length match {
      case length if length == numberOfTableauPiles => (tableauPiles, cards)
      case length =>
        val newTableauPiles =  tableauPiles ::: new TableauPile(cards.take(length + 1)) :: Nil
        val remainingCards = cards.takeRight(cards.length - (length + 1))
        distributeCardsInTableauPiles(numberOfTableauPiles, newTableauPiles, remainingCards)
    }
  }

}
