package klondike.models

import klondike.test_utils.TestModels._
import org.scalatest.FunSuite

class FoundationTest extends FunSuite with PileBehaviors {

  testsFor(emptyPileBehaviors(emptyFoundation))
  testsFor(pileWithOneCardBehaviors(foundationWithCard(aceOfGolds)))

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
    assert(!emptyFoundation.full)
  }

  test("givenAFoundation_whenPuttingACardWithMinValueOnIt_thenFoundationIsNotFull") {
    assert(!emptyFoundation.put(aceOfGolds).full)
  }

  test("givenAFoundation_whenPuttingACardWithMaxValueOnIt_thenFoundationIsFull") {
    assert(emptyFoundation.put(kingOfSwords).full)
  }
}
