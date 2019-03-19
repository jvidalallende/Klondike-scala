package klondike.models

import org.scalatest.FunSuite

class FoundationTest extends FunSuite with PileBehaviors {

  val aceOfGolds = new SpanishCard(1, "golds")
  val twoOfGolds = new SpanishCard(2, "golds")
  val kingOfSwords = new SpanishCard(10, "swords")

  testsFor(emptyPileBehaviors(new Foundation(Nil)))
  testsFor(pileWithOneCardBehaviors(new Foundation(aceOfGolds :: Nil)))

  test("givenAFoundationWithTwoCards_whenUsingConstructorFromPile_thenNewFoundationIsEqualToOriginalOne") {
    val original = new Foundation(twoOfGolds :: aceOfGolds :: Nil)
    val copy = new Foundation(original)
    assert(original == copy)
  }

  test("givenTwoDownturnedCards_whenBuildingAFoundationWithIt_thenCardsInTheFoundationAreUpturn") {
    val foundation = new Foundation(twoOfGolds :: aceOfGolds :: Nil)
    assert(twoOfGolds.upturn() :: aceOfGolds.upturn() :: Nil == foundation.cards)
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
