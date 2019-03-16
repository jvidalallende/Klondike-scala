package klondike.models

import klondike.exceptions.InvalidValueException
import org.scalatest.FunSuite

class GameTest extends FunSuite {

  private val aceOfGolds = new SpanishCard(1, "golds")
  private val twoOfGolds = new SpanishCard(2, "golds")
  private val twoOfCups = new SpanishCard(2, "cups")

  private val deck = new Deck(Nil)
  private val waste = new Waste(aceOfGolds :: Nil)
  private val emptyFoundation = new Foundation(Nil)
  private val emptyTableauPile = new TableauPile(Nil)
  private val tableauPile = new TableauPile(twoOfGolds :: twoOfCups.downturn() :: Nil)

  test("givenAGames_whenCheckingIfIsEqualToAGameWithTheSameBoard_thenTheyAreEqual") {
    val board = new Board(deck, waste, emptyFoundation :: emptyFoundation :: Nil, emptyTableauPile :: tableauPile :: Nil)
    val game = new Game(board)
    assert(game == new Game(game.board))
  }

  test("givenAGameWithABoard_whenComparingTheGameToTheBoard_thenTheyAreNotEqual") {
    val board = new Board(deck, waste, emptyFoundation :: emptyFoundation :: Nil, emptyTableauPile :: tableauPile :: Nil)
    val game = new Game(board)
    assert(game != board)
  }
}
