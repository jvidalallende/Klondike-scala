package klondike.models

import klondike.exceptions.EmptyPileException
import org.scalatest.FunSuite

class FoundationTest extends FunSuite {

  val aceOfGolds = new SpanishCard(1, "golds")
  val twoOfCups = new SpanishCard(2, "cups")
  val kingOfSwords = new SpanishCard(10, "swords")

  test("givenOneEmptyFoundation_whenPickingFromIt_thenRaisesException") {
    intercept[EmptyPileException] {
      new Foundation(Nil).pick()
    }
  }

  test("givenOneEmptyFoundation_whenCheckingIfItIsEmpty_thenItIsEmpty") {
    assert(new Foundation(Nil).empty)
  }

  test("givenOneFoundationWithOneCard_whenCheckingIfItIsEmpty_thenItIsNotEmpty") {
    assert(!new Foundation(aceOfGolds :: Nil).empty)
  }

  test("givenAFoundation_whenComparedToAListOfCards_thenTheyAreNotEqual") {
    val cards = List(aceOfGolds.upturn(), twoOfCups)
    assert(new Foundation(cards) != cards)
  }

  test("givenTwoDownturnedCards_whenBuildingAFoundationWithIt_thenCardsInTheFoundationAreUpturn") {
    val foundation = new Foundation(aceOfGolds :: twoOfCups :: Nil)
    assert(aceOfGolds.upturn() :: twoOfCups.upturn() :: Nil == foundation.cards)
  }

  test("givenAFoundationWithOneCard_whenPickingFromIt_thenReturnsTheCardAndAnEmptyFoundation") {
    val aceOfGolds = new SpanishCard(1, "golds")
    val foundation = new Foundation(aceOfGolds :: Nil)
    assert((aceOfGolds.upturn(), new Foundation(Nil)) == foundation.pick())
  }

  test("givenAnEmptyFoundation_whenCheckingIfItIsFull_thenItReturnsFalse") {
    val foundation = new Foundation(Nil)
    assert(!foundation.full())
  }

  test("givenAFoundation_whenPuttingACardWithMinValueOnIt_thenFoundationIsNotFull") {
    val foundation = new Foundation(Nil).put(aceOfGolds)
    assert(!foundation.full())
  }

  test("givenAFoundation_whenPuttingACardWithMaxValueOnIt_thenFoundationIsFull") {
    val foundation = new Foundation(Nil).put(kingOfSwords)
    assert(foundation.full())
  }
}
