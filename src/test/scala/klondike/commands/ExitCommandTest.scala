package klondike.commands

import klondike.exceptions.ExitGameException
import klondike.models._
import org.scalatest.FunSuite

class ExitCommandTest extends FunSuite {

  test("givenABoard_whenExecutingExitCommand_thenExitExceptionIsRaised") {
    val board = new Board(new Deck(Nil), new Waste(Nil), Nil, Nil)
    intercept[ExitGameException] {
      new ExitCommand("").execute(board)
    }
  }
}
