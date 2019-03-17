package klondike.commands

import klondike.exceptions.ExitGameException
import klondike.models._
import org.scalatest.FunSuite

class ExitCommandTest extends FunSuite {

  test("givenAGame_whenExecutingExitCommand_thenExitExceptionIsRaised") {
    val game = new Game(new Board(new Deck(Nil), new Waste(Nil), Nil, Nil))
    intercept[ExitGameException] {
      Commands.exit.execute(game)
    }
  }
}
