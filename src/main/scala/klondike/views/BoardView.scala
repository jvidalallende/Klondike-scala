package klondike.views

import klondike.models.{Board, Foundation}

object BoardView {

  def draw[A](board: Board, cardView: CardView, io: IOManager): Unit = {
    drawDeckAndWaste(board, cardView, io)
    drawSpaceBetweenWasteAndFoundations(io)
    drawFoundations(board.foundations, cardView, io)
  }

  def drawDeckAndWaste(board: Board, cardView: CardView, io: IOManager): Unit = {
    OnlyTopCardPileView.draw(board.deck, cardView, io)
    io.write(" ")
    OnlyTopCardPileView.draw(board.waste, cardView, io)
  }

  def drawSpaceBetweenWasteAndFoundations(io: IOManager): Unit = {
    io.write(" ")
    io.write("       ")
    io.write(" ")
  }

  def drawFoundations(foundations: List[Foundation], cardView: CardView, io: IOManager): Unit = {
    OnlyTopCardPileView.draw(foundations.head, cardView, io)
    for (foundation <- foundations.tail) {
      io.write(" ")
      OnlyTopCardPileView.draw(foundation, cardView, io)
    }
  }
}
