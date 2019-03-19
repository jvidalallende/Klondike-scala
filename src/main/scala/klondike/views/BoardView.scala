package klondike.views

import klondike.models._

class BoardView(__cardView: CardView, __io: IOManager) {

  private val _cardView = __cardView
  private val _io = __io

  def draw(board: Board): Unit = {
    drawTopRow(board)
    drawTableauPilesRow(board.tableauPiles)
  }

  private def drawTopRow(board: Board): Unit = {
    drawDeckAndWaste(board)
    drawSpaceBetweenWasteAndFoundations(_io)
    drawTopCardOfPileList(board.foundations)
    _io.write("\n")
  }

  private def drawDeckAndWaste(board: Board): Unit = {
    OnlyTopCardPileView.draw(board.deck, _cardView, _io)
    _io.write(" ")
    OnlyTopCardPileView.draw(board.waste, _cardView, _io)
  }

  private def drawSpaceBetweenWasteAndFoundations(_io: IOManager): Unit = {
    _io.write(" ")
    _cardView.drawEmpty(_io)
    _io.write(" ")
  }

  private def drawTopCardOfPileList(piles: List[Pile]): Unit = {
    OnlyTopCardPileView.draw(piles.head, _cardView, _io)
    for (foundation <- piles.tail) {
      _io.write(" ")
      OnlyTopCardPileView.draw(foundation, _cardView, _io)
    }
    _io.write("\n")
  }

  private def drawTableauPilesRow(tableauPiles: List[TableauPile]): Unit = {
    val reversedTableauPiles = tableauPiles.map(tp => new TableauPile(tp.cards.reverse))
    drawTopCardOfPileList(reversedTableauPiles)
    val remainingCards = reversedTableauPiles.map(tp => removeFirst(tp.cards))
    drawRemainingCards(remainingCards)
    _io.write("\n")
  }

  private def removeFirst[A](cards: List[A]): List[A] = {
    cards match {
      case Nil => Nil
      case _ => cards.tail
    }
  }

  private def drawRemainingCards(remainingCards: List[List[Card]]): Unit = {
    remainingCards match {
      case _ if remainingCards.flatten.isEmpty =>
      case _ =>
        drawRemainingCardsRow(remainingCards)
        drawRemainingCards(remainingCards.map(x => removeFirst(x)))
    }
  }

  private def drawRemainingCardsRow(remainingCards: List[List[Card]]): Unit = {
    drawCardColumn(remainingCards.head)
    for (cardColumn <- remainingCards.tail) {
      _io.write(" ")
      drawCardColumn(cardColumn)
    }
    _io.write("\n")
  }

  private def drawCardColumn(cardColumn: List[Card]): Unit = {
    if (cardColumn.isEmpty) {
      _cardView.drawEmpty(_io)
    } else {
      _cardView.draw(cardColumn.head, _io)
    }
  }
}
