package klondike.views

import klondike.models._

import scala.util.Random

object SpanishBoardBuilder {

  def build(): Board = {
    val (tableauPiles, deckCards) = createTableauPiles(createCards())
    val emptyFoundations = new Foundation(Nil) :: new Foundation(Nil) :: new Foundation(Nil) :: new Foundation(Nil) :: Nil
    new Board(new Deck(deckCards), new Waste(Nil), emptyFoundations, tableauPiles)
  }

  private def createCards(): List[SpanishCard] = {
    val cardSequence = for (value <- SpanishCard.MIN_VALUE to SpanishCard.MAX_VALUE; suit <- SpanishCard.suits) yield new SpanishCard(value, suit)
    Random.shuffle(cardSequence).toList
  }

  private def createTableauPiles(cards: List[SpanishCard]): (List[TableauPile], List[SpanishCard]) = {
    def distributeCardsInTableauPiles(tableauPiles: List[TableauPile], cards: List[SpanishCard]): (List[TableauPile], List[SpanishCard]) = {
      tableauPiles.length match {
        case 7 => (tableauPiles, cards)
        case len => distributeCardsInTableauPiles(tableauPiles ::: new TableauPile(cards.take(len + 1)) :: Nil, cards.takeRight(cards.length - (len + 1)))
      }
    }

    val (downturnedTableauPiles, remainingCards) = distributeCardsInTableauPiles(Nil, cards)
    val tableauPiles = downturnedTableauPiles.map(tp => new TableauPile(tp.cards.head.upturn() :: tp.cards.tail))
    (tableauPiles, remainingCards)
  }
}
