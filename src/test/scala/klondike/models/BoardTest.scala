package klondike.models

import klondike.exceptions.InvalidValueException
import org.scalatest.FunSuite

class BoardTest extends FunSuite {

  private val aceOfGolds = new SpanishCard(1, "golds")
  private val twoOfGolds = new SpanishCard(2, "golds")
  private val twoOfCups = new SpanishCard(2, "cups")

  private val deck = new Deck(Nil)
  private val waste = new Waste(aceOfGolds :: Nil)
  private val emptyFoundation = new Foundation(Nil)
  private val emptyTableauPile = new TableauPile(Nil)
  private val tableauPile = new TableauPile(twoOfGolds :: twoOfCups.downturn() :: Nil)

  test("givenABoardWithDeckWasteAndEmptyFoundationsAndTableauPiles_whenRetrievingThePiles_thenTheyAreCorrectlyReturned") {
    val board = new Board(deck, waste, Nil, Nil)
    assert(deck == board.deck)
    assert(waste == board.waste)
    assert(Nil == board.foundations)
    assert(Nil == board.tableauPiles)
  }

  test("givenABoardWithNoFoundations_whenRetrievingTheFirstFoundation_thenExceptionIsRaised") {
    val board = new Board(deck, waste, Nil, Nil)
    intercept[InvalidValueException] {
      board.foundation(0)
    }
  }

  test("givenABoardWithFourFoundations_whenRetrievingTheFifthFoundation_thenExceptionIsRaised") {
    val board = new Board(deck, waste, emptyFoundation :: emptyFoundation :: emptyFoundation :: emptyFoundation :: Nil, Nil)
    intercept[InvalidValueException] {
      board.foundation(4)
    }
  }

  test("givenABoardWithSevenTableauPiles_whenRetrievingTheThirdOne_thenItIsCorrectlyReturned") {
    val tableauPiles = emptyTableauPile :: emptyTableauPile :: tableauPile :: emptyTableauPile :: emptyTableauPile :: emptyTableauPile :: emptyTableauPile :: Nil
    val board = new Board(deck, waste, Nil, tableauPiles)
    assert(tableauPile == board.tableauPile(2))
  }

  test("givenTwoBoardsWithTheSamePilesPiles_whenCheckingIfTheyAreEqual_thenTrueIsReturned") {
    val board = new Board(deck, waste, emptyFoundation :: emptyFoundation :: Nil, emptyTableauPile :: tableauPile :: Nil)
    val board2 = new Board(deck, waste, emptyFoundation :: emptyFoundation :: Nil, emptyTableauPile :: tableauPile :: Nil)
    assert(board == board2)
  }

  test("givenABoard_whenComparedToAString_thenTheyAreNotEqual") {
    val board = new Board(deck, waste, emptyFoundation :: emptyFoundation :: Nil, emptyTableauPile :: tableauPile :: Nil)
    assert(board != "board")
  }
}
