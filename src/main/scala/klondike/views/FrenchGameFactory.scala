package klondike.views

import klondike.models.{Board, Card, Pile}

object FrenchGameFactory extends GameFactory {

  override val name = "FrenchGameFactory"

  override def cardView: CardView = FrenchCardView

  override def initialBoard(numberOfTableauPiles: Int): Board = FrenchBoardBuilder.build(numberOfTableauPiles)

  override def tableauPileValidator: (Card, Pile) => Boolean = {
    (card: Card, pile: Pile) => {
      pile match {
        case f if f.empty => card.isMax
        case _ => differentColor(card, pile.cards.head) && (pile.cards.head.value - card.value == 1)
      }
    }
  }

  private def differentColor(card: Card, other: Card): Boolean = {
    def isRed(c: Card): Boolean = c.suit == "hearts" || c.suit == "diamonds"

    def isBlack(c: Card): Boolean = c.suit == "spades" || c.suit == "clubs"

    card.suit match {
      case "hearts" => isBlack(other)
      case "diamonds" => isBlack(other)
      case "spades" => isRed(other)
      case "clubs" => isRed(other)
      case _ => false
    }
  }

}
