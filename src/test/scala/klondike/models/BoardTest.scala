package klondike.models

import klondike.test_utils.TestModels._
import klondike.exceptions.InvalidValueException
import org.scalatest.FunSuite

class BoardTest extends FunSuite {

  private val tableauPile = new TableauPile(twoOfGolds :: twoOfClubsSpanish.downturn() :: Nil)

  test("givenAnEmptyBoard_whenRetrievingTheFifthFoundation_thenExceptionIsRaised") {
    intercept[InvalidValueException] {
      emptyBoard.foundation(5)
    }
  }

  test("givenABoardWithSevenTableauPiles_whenRetrievingTheThirdOne_thenItIsCorrectlyReturned") {
    val board = emptyBoardWithTableauPile(tableauPile, 2)
    assert(tableauPile == board.tableauPile(2))
  }

  test("givenTwoBoardsWithTheSamePilesPiles_whenCheckingIfTheyAreEqual_thenTrueIsReturned") {
    val board = emptyBoardWithTableauPile(tableauPile, 2)
    val board2 = emptyBoardWithTableauPile(tableauPile, 2)
    assert(board == board2)
  }

  test("givenABoard_whenComparedToAString_thenTheyAreNotEqual") {
    assert(emptyBoard != "board")
  }
}
