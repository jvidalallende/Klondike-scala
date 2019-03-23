package klondike.models

import klondike.test_utils.TestModels._
import org.scalatest.FunSuite

class GameTest extends FunSuite {

  test("givenAGame_whenCheckingIfIsEqualToAGameWithTheSameBoard_thenTheyAreEqual") {
    val game = new Game(emptyBoard)
    assert(game == new Game(game.board))
  }

  test("givenAGameWithABoard_whenComparingTheGameToTheBoard_thenTheyAreNotEqual") {
    val game = new Game(emptyBoard)
    assert(game != emptyBoard)
  }
}
