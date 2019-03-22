package klondike.views

import klondike.models._

import scala.util.Random

class SpanishGameFactory(__numberOfTableauPiles: Int) extends GameFactory {

  private val _numberOfTableauPiles = __numberOfTableauPiles

  // Responsible of drawing the cards
  override def cardView: CardView = SpanishCardView

  // Returns a function that indicates if a Card is allowed to be placed in a TableauPile
  override def tableauPileValidator: (Card, TableauPile) => Boolean = {
    (card: Card, tableauPile: TableauPile) => {
      tableauPile match {
        case f if f.empty => card.isMax
        case _ =>
          val topOfTableauPile = tableauPile.cards.head
          (card.suit != topOfTableauPile.suit) && (topOfTableauPile.value - card.value == 1)
      }
    }
  }

  // Builds the initial game board, with all the cards in it distributed among the Piles
  override def initialBoard: Board = {
    val (tableauPiles, deckCards) = createTableauPiles(createCards())
    val emptyFoundations = List(Nil, Nil, Nil, Nil).map(x => new Foundation(x))
    new Board(new Deck(deckCards), new Waste(Nil), emptyFoundations, tableauPiles)
  }

  private def createCards(): List[SpanishCard] = {
    val cardSequence = for (value <- SpanishCard.MIN_VALUE to SpanishCard.MAX_VALUE; suit <- SpanishCard.suits) yield new SpanishCard(value, suit)
    Random.shuffle(cardSequence).toList
  }

  private def createTableauPiles(cards: List[SpanishCard]): (List[TableauPile], List[SpanishCard]) = {
    val (downturnedTableauPiles, remainingCards) = distributeCardsInTableauPiles(Nil, cards)
    val tableauPiles = downturnedTableauPiles.map(tp => new TableauPile(tp.cards.head.upturn() :: tp.cards.tail))
    (tableauPiles, remainingCards)
  }

  private def distributeCardsInTableauPiles(tableauPiles: List[TableauPile], cards: List[SpanishCard]): (List[TableauPile], List[SpanishCard]) = {
    tableauPiles.length match {
      case _numberOfTableauPiles => (tableauPiles, cards)
      case len => distributeCardsInTableauPiles(tableauPiles ::: new TableauPile(cards.take(len + 1)) :: Nil, cards.takeRight(cards.length - (len + 1)))
    }
  }
}
