package klondike.views

import klondike.models.{Board, Card, Pile}

object SpanishGameFactory extends GameFactory {

  override val name = "SpanishGameFactory"

  override def cardView: CardView = SpanishCardView

  override def tableauPileValidator: (Card, Pile) => Boolean = {
    (card: Card, pile: Pile) => {
      pile match {
        case f if f.empty => card.isMax
        case _ => (card.suit != pile.cards.head.suit) && (pile.cards.head.value - card.value == 1)
      }
    }
  }

  override def initialBoard(numberOfTableauPiles: Int): Board = SpanishBoardBuilder.build(7)
}
