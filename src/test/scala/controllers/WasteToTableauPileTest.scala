package controllers

import exceptions.{EmptyPileException, InvalidMoveException}
import models.{SpanishCard, TableauPile, Waste}
import org.scalatest.FunSuite

class WasteToTableauPileTest extends FunSuite {

  private val emptyWaste = new Waste(Nil)
  private val emptyTableauPile = new TableauPile(Nil)

  private val aceOfGolds = new SpanishCard(1, "golds", true)
  private val twoOfClubs = new SpanishCard(2, "clubs", true)
  private val kingOfSwords = new SpanishCard(SpanishCard.MAX_VALUE, "swords", true)

  private val move = new MovementFactory().wasteToTableauPile()

  test("givenEmptyWasteAndTableauPile_whenMovingFromWasteToTableauPile_thenExceptionIsThrown") {
    intercept[EmptyPileException] {
      move(emptyWaste, emptyTableauPile)
    }
  }

  test("givenAWasteWithAKing_whenMovingToAnEmptyTableauPile_thenNewWasteIsEmptyAndTableauPileContainsTheCard") {
    val waste = new Waste(kingOfSwords :: Nil)
    val result = move(waste, emptyTableauPile)
    assert(result._1.empty())
    assert(kingOfSwords :: Nil == result._2.cards())
  }

  test("givenAWasteWithACardDifferentFromAKing_whenMovingToAnEmptyTableauPile_thenExceptionIsThrown") {
    val waste = new Waste(aceOfGolds :: Nil)
    intercept[InvalidMoveException] {
      move(waste, emptyTableauPile)
    }
  }

  test("givenAWasteWithOneCard_whenMovingToATableauPileThatCanReceiveThatCard_thenNewWasteIsEmptyAndTableauPilesContainsTheCards") {
    val waste = new Waste(aceOfGolds :: Nil)
    val tableauPile = new TableauPile(twoOfClubs :: Nil)
    val result = move(waste, tableauPile)
    assert(result._1.empty())
    assert(aceOfGolds :: tableauPile.cards() == result._2.cards())
  }

  test("givenAWasteWithACard_whenMovingToATableauPileThatCannotReceiveThatCard_thenExceptionIsThrown") {
    val waste = new Waste(twoOfClubs :: Nil)
    val tableauPile = new TableauPile(aceOfGolds :: Nil)
    intercept[InvalidMoveException] {
      move(waste, tableauPile)
    }
  }
}
