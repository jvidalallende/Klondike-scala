package klondike.models

import klondike.exceptions.EmptyPileException
import klondike.test_utils.TestModels._

import org.scalatest.FunSuite

class TableauPileTest extends FunSuite with PileBehaviors {

  testsFor(emptyPileBehaviors(emptyTableauPile))
  testsFor(pileWithOneCardBehaviors(tableauPileWithCard(aceOfGolds)))

  test("givenATableauPileWithTwoCards_whenUsingConstructorFromPile_thenNewTableauPileIsEqualToOriginalOne") {
    val original = new TableauPile(aceOfGolds :: twoOfClubs :: Nil)
    val copy = new TableauPile(original)
    assert(original == copy)
  }

  test("givenATableauPileWithOneUpturnedAndOneDownturnedCard_whenPickingFromIt_thenTheCardInThePileIsUpturned") {
    val tableauPile = new TableauPile(aceOfGolds :: twoOfClubs.downturn() :: Nil)
    val (_, newTableauPile) = tableauPile.pick()
    assert(newTableauPile.cards.head.upturned)
  }

  test("givenATableauPileWithOneUpturnedCard_whenPickingTwoCardsFromIt_thenExceptionIsThrown") {
    intercept[EmptyPileException] {
      tableauPileWithCard(aceOfGolds).pick(2)
    }
  }

  test("givenATableauPileWithOneUpturnedCard_whenPickingZeroCardsFromIt_thenSamePileIsReturned") {
    val tableauPile = tableauPileWithCard(aceOfGolds)
    assert((Nil, tableauPile) == tableauPile.pick(0))
  }

  test("givenATableauPileWithOneUpturnedAndOneDownturnedCard_whenPickingTwoCardsFromIt_thenExceptionIsThrown") {
    intercept[EmptyPileException] {
      new TableauPile(aceOfGolds :: twoOfClubs.downturn() :: Nil).pick(2)
    }
  }

  test("givenATableauPileWithTwoUpturnedAndOneDownturnedCard_whenPickingTwoCardsFromIt_thenPickedCardsComeInOrderAndCardInThePileIsUpturn") {
    val tableauPile = new TableauPile(aceOfGolds :: twoOfClubs :: kingOfSwords.downturn() :: Nil)
    val (picked, newTableauPile) = tableauPile.pick(2)
    assert(twoOfClubs :: aceOfGolds :: Nil == picked)
    assert(newTableauPile.cards.head.upturned)
  }

  test("givenAnEmptyTableauPile_whenPuttingOneCard_thenThePileHeadIsThePutCard") {
    assert(aceOfGolds == emptyTableauPile.put(aceOfGolds).cards.head)
  }

  test("givenAnEmptyTableauPile_whenPuttingTwoCards_thenThePileContainsTheCardsInReversedOrder") {
    val tableauPile = emptyTableauPile.put(aceOfGolds :: twoOfClubs :: Nil)
    assert(twoOfClubs == tableauPile.cards.head)
    assert(aceOfGolds == tableauPile.cards.tail.head)
  }

  test("givenATableauPileWithTwoUpturnedCards_whenPickingAndPuttingTheSameTwoCards_thenResultIsTheOriginalPile") {
    val tableauPile = new TableauPile(aceOfGolds :: twoOfClubs :: Nil)
    val (picked, tempTableauPile) = tableauPile.pick(2)
    assert(tableauPile == tempTableauPile.put(picked))
  }
}
