package controllers

import exceptions.{EmptyPileException, InvalidMoveException}
import models.{SpanishCard, TableauPile}
import org.scalatest.FunSuite

class TableauPileToTableauPileTest extends FunSuite {

  private val emptyTableauPile = new TableauPile(Nil)

  private val aceOfGolds = new SpanishCard(1, "golds", true)
  private val twoOfClubs = new SpanishCard(2, "clubs", true)
  private val kingOfSwords = new SpanishCard(SpanishCard.MAX_VALUE, "swords", true)

  private val move = new MovementFactory().tableauPileToTableauPile()

  test("givenTwoEmptyTableauPiles_whenMovingFromTableauPileToTableauPile_thenExceptionIsThrown") {
    intercept[EmptyPileException] {
      move(emptyTableauPile, emptyTableauPile, 1)
    }
  }

  test("givenATableauPileWithAKing_whenMovingToAnEmptyTableauPile_thenTheFirstTableauIsEmptyAndTheOtherTableauPileContainsTheCard") {
    val waste = new TableauPile(kingOfSwords :: Nil)
    val result = move(waste, emptyTableauPile, 1)
    assert(result._1.empty())
    assert(kingOfSwords :: Nil == result._2.cards())
  }

  test("givenATableauPileWithACardDifferentFromAKing_whenMovingToAnEmptyTableauPile_thenExceptionIsThrown") {
    val waste = new TableauPile(aceOfGolds :: Nil)
    intercept[InvalidMoveException] {
      move(waste, emptyTableauPile, 1)
    }
  }

  test("givenATableauPileWithOneCard_whenMovingToATableauPileThatCanReceiveThatCard_thenNewTableauPileIsEmptyAndTableauPilesContainsTheCards") {
    val waste = new TableauPile(aceOfGolds :: Nil)
    val tableauPile = new TableauPile(twoOfClubs :: Nil)
    val result = move(waste, tableauPile, 1)
    assert(result._1.empty())
    assert(aceOfGolds :: tableauPile.cards() == result._2.cards())
  }

  test("givenATableauPileWithACard_whenMovingToATableauPileThatCannotReceiveThatCard_thenExceptionIsThrown") {
    val waste = new TableauPile(twoOfClubs :: Nil)
    val tableauPile = new TableauPile(aceOfGolds :: Nil)
    intercept[InvalidMoveException] {
      move(waste, tableauPile, 1)
    }
  }
}
