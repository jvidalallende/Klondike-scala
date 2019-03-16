package klondike.models

import klondike.exceptions.EmptyPileException
import org.scalatest.FunSuite

class FoundationTest extends FunSuite {

  val aceOfGolds = new SpanishCard(1, "golds")
  val twoOfGolds = new SpanishCard(2, "golds")
  val kingOfSwords = new SpanishCard(10, "swords")

  test("givenAEmptyFoundation_whenPickingFromIt_thenRaisesException") {
    intercept[EmptyPileException] {
      new Foundation(Nil).pick()
    }
  }

  test("givenAEmptyFoundation_whenCheckingIfItIsEmpty_thenItIsEmpty") {
    assert(new Foundation(Nil).empty)
  }

  test("givenAFoundationWithOneCard_whenCheckingIfItIsEmpty_thenItIsNotEmpty") {
    assert(!new Foundation(aceOfGolds :: Nil).empty)
  }

  test("givenAFoundation_whenComparedToAListOfCards_thenTheyAreNotEqual") {
    val cards = List(twoOfGolds.upturn(), aceOfGolds)
    assert(new Foundation(cards) != cards)
  }

  test("givenAFoundationWithTwoCards_whenUsingConstructorFromPile_thenNewFoundationIsEqualToOriginalOne") {
    val original = new Foundation(twoOfGolds :: aceOfGolds :: Nil)
    val copy = new Foundation(original)
    assert(original == copy)
  }

  test("givenTwoDownturnedCards_whenBuildingAFoundationWithIt_thenCardsInTheFoundationAreUpturn") {
    val foundation = new Foundation(twoOfGolds :: aceOfGolds :: Nil)
    assert(twoOfGolds.upturn() :: aceOfGolds.upturn() :: Nil == foundation.cards)
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
