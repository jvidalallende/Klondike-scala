package models

import exceptions.EmptyPileException
import org.scalatest.FunSuite

class TableauPileTest extends FunSuite {

  val aceOfGolds = new SpanishCard(1, "golds", true)
  val twoOfCups = new SpanishCard(2, "cups", true)
  val kingOfSwords = new SpanishCard(10, "swords", true)

  test("givenAnEmptyTableauPile_whenPickingFromIt_thenItExceptionIsThrown") {
    intercept[EmptyPileException] {
      new TableauPile(Nil).pick()
    }
  }

  test("givenATableauPileWithOneCard_whenPickingFromIt_thenItBecomesEmpty") {
    val tableauPile = new TableauPile(aceOfGolds :: Nil)
    val (picked, newTableauPile) = tableauPile.pick()
    assert(aceOfGolds == picked)
    assert(newTableauPile.empty())
  }

  test("givenATableauPileWithOneUpturnedAndOneDownturnedCard_whenPickingFromIt_thenTheCardInThePileIsUpturned") {
    val tableauPile = new TableauPile(aceOfGolds :: twoOfCups.downturn() :: Nil)
    val (_, newTableauPile) = tableauPile.pick()
    assert(newTableauPile.cards().head.upturned())
  }

  test("givenATableauPileWithOneUpturnedCard_whenPickingTwoCardsFromIt_thenExceptionIsThrown") {
    intercept[EmptyPileException] {
      new TableauPile(aceOfGolds :: Nil).pick(2)
    }
  }

  test("givenATableauPileWithOneUpturnedCard_whenPickingZeroCardsFromIt_thenSamePileIsReturned") {
    val tableauPile = new TableauPile(aceOfGolds :: Nil)
    assert((Nil, tableauPile) == tableauPile.pick(0))
  }

  test("givenATableauPileWithOneUpturnedAndOneDownturnedCard_whenPickingTwoCardsFromIt_thenExceptionIsThrown") {
    intercept[EmptyPileException] {
      new TableauPile(aceOfGolds :: twoOfCups.downturn() :: Nil).pick(2)
    }
  }

  test("givenATableauPileWithTwoUpturnedAndOneDownturnedCard_whenPickingTwoCardsFromIt_thenPickedCardsComeInOrderAndCardInThePileIsUpturn") {
    val tableauPile = new TableauPile(aceOfGolds :: twoOfCups :: kingOfSwords.downturn() :: Nil)
    val (picked, newTableauPile) = tableauPile.pick(2)
    assert(twoOfCups :: aceOfGolds :: Nil == picked)
    assert(newTableauPile.cards().head.upturned())
  }

  test("givenAnEmptyTableauPile_whenPuttingOneCard_thenThePileHeadIsThePutCard") {
    assert(aceOfGolds == new TableauPile(Nil).put(aceOfGolds).cards().head)
  }

  test("givenAnEmptyTableauPile_whenPuttingTwoCards_thenThePileContainsTheCardsInReversedOrder") {
    val tableauPile = new TableauPile(Nil).put(aceOfGolds :: twoOfCups :: Nil)
    assert(twoOfCups == tableauPile.cards().head)
    assert(aceOfGolds == tableauPile.cards().tail.head)
  }

  test("givenATableauPileWithTwoUpturnedCards_whenPickingAndPuttingTheSameTwoCards_thenResultIsTheOriginalPile") {
    val tableauPile = new TableauPile(aceOfGolds :: twoOfCups :: Nil)
    val (picked, tempTableauPile) = tableauPile.pick(2)
    assert(tableauPile == tempTableauPile.put(picked))
  }
}
