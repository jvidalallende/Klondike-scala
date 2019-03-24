package klondike.views

import klondike.models.Board
import klondike.test_utils.IOMocks
import org.scalatest.FunSuite

trait BoardViewBehaviors {
  this: FunSuite =>

  def boardViewDraw(boardName: String, board: Board, cardView: CardView, expected: String) {

    test(s"givenAn${boardName}_whenItsViewDrawsIt_thenItIsCorrectlyPrinted") {
      val mockIO = IOMocks.writeAccumulator
      val boardView = new BoardView(cardView, mockIO)
      boardView.draw(board)
      val actual = mockIO.asString
      assert(expected == mockIO.asString)
    }
  }

}
