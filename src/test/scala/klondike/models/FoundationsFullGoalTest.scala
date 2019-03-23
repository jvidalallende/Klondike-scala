package klondike.models

import klondike.test_utils.TestModels._
import klondike.utils.ListHelpers
import org.scalatest.FunSuite

class FoundationsFullGoalTest extends FunSuite {

  private val fullFoundations = List(kingOfGolds, kingOfCups, kingOfSwords, kingOfClubs).map(x => new Foundation(x :: Nil))

  test("givenABoardWithEmptyFoundations_whenCheckingFoundationsFullGoal_thenItIsFalse") {
    assert(!FoundationsFullGoal.done(emptyBoard))
  }

  test("givenABoardWithFullFoundations_whenCheckingFoundationsFullGoal_thenItIsTrue") {
    assert(FoundationsFullGoal.done(emptyBoardWithFoundations(fullFoundations)))
  }

  test("givenABoardWithOnlyOneFoundationFull_whenCheckingFoundationsFullGoal_thenItIsFalse") {
    assert(!FoundationsFullGoal.done(emptyBoardWithFoundation(foundationWithCard(kingOfGolds), 0)))
  }

  test("givenABoardWithAllButOneFoundationFull_whenCheckingFoundationsFullGoal_thenItIsFalse") {
    val foundations = ListHelpers.replaceAt(fullFoundations, 0, new Foundation(Nil))
    assert(!FoundationsFullGoal.done(emptyBoardWithFoundations(foundations)))
  }
}
