package klondike.models

import klondike.utils.ListHelpers
import org.scalatest.FunSuite

class FoundationsFullGoalTest extends FunSuite {

  private val kingOfGolds = new SpanishCard(SpanishCard.MAX_VALUE, "golds")
  private val kingOfCups = new SpanishCard(SpanishCard.MAX_VALUE, "cups")
  private val kingOfSwords = new SpanishCard(SpanishCard.MAX_VALUE, "swords")
  private val kingOfClubs = new SpanishCard(SpanishCard.MAX_VALUE, "clubs")

  private val fullFoundations = List(kingOfGolds, kingOfCups, kingOfSwords, kingOfClubs).map(x => new Foundation(x :: Nil))
  private val emptyFoundations = List(Nil, Nil, Nil, Nil).map(x => new Foundation(x))

  private val emptyBoard = new Board(new Deck(Nil), new Waste(Nil), emptyFoundations, Nil)
  private val foundationsFullBoard = new Board(new Deck(Nil), new Waste(Nil), fullFoundations, Nil)

  test("givenABoardWithEmptyFoundations_whenCheckingFoundationsFullGoal_thenItIsFalse") {
    assert(!FoundationsFullGoal.done(emptyBoard))
  }

  test("givenABoardWithFullFoundations_whenCheckingFoundationsFullGoal_thenItIsTrue") {
    assert(FoundationsFullGoal.done(foundationsFullBoard))
  }

  test("givenABoardWithOnlyOneFoundationFull_whenCheckingFoundationsFullGoal_thenItIsFalse") {
    val foundations = ListHelpers.replaceAt(emptyFoundations, 0, new Foundation(kingOfGolds :: Nil))
    val board = new Board(new Deck(Nil), new Waste(Nil), foundations, Nil)
    assert(!FoundationsFullGoal.done(board))
  }

  test("givenABoardWithAllButOneFoundationFull_whenCheckingFoundationsFullGoal_thenItIsFalse") {
    val foundations = ListHelpers.replaceAt(fullFoundations, 0, new Foundation(Nil))
    val board = new Board(new Deck(Nil), new Waste(Nil), foundations, Nil)
    assert(!FoundationsFullGoal.done(board))
  }
}
