package controllers

import exceptions.{EmptyPileException, InvalidMoveException}
import models.{SpanishCard, TableauPile, Foundation}
import org.scalatest.FunSuite

class FoundationToTableauPileTest extends FunSuite {

  private val emptyFoundation = new Foundation(Nil)
  private val emptyTableauPile = new TableauPile(Nil)

  private val aceOfGolds = new SpanishCard(1, "golds", true)
  private val twoOfClubs = new SpanishCard(2, "clubs", true)
  private val kingOfSwords = new SpanishCard(SpanishCard.MAX_VALUE, "swords", true)

  private val move = new MovementFactory().foundationToTableauPile()

  test("givenEmptyFoundationAndTableauPile_whenMovingFromFoundationToTableauPile_thenExceptionIsThrown") {
    intercept[EmptyPileException] {
      move(emptyFoundation, emptyTableauPile)
    }
  }

  test("givenAFoundationWithAKing_whenMovingToAnEmptyTableauPile_thenNewFoundationIsEmptyAndTableauPileContainsTheCard") {
    val foundation = new Foundation(kingOfSwords :: Nil)
    val result = move(foundation, emptyTableauPile)
    assert(result._1.empty())
    assert(kingOfSwords :: Nil == result._2.cards())
  }

  test("givenAFoundationWithACardDifferentFromAKing_whenMovingToAnEmptyTableauPile_thenExceptionIsThrown") {
    val foundation = new Foundation(aceOfGolds :: Nil)
    intercept[InvalidMoveException] {
      move(foundation, emptyTableauPile)
    }
  }

  test("givenAFoundationWithOneCard_whenMovingToATableauPileThatCanReceiveThatCard_thenNewFoundationIsEmptyAndTableauPilesContainsTheCards") {
    val foundation = new Foundation(aceOfGolds :: Nil)
    val tableauPile = new TableauPile(twoOfClubs :: Nil)
    val result = move(foundation, tableauPile)
    assert(result._1.empty())
    assert(aceOfGolds :: tableauPile.cards() == result._2.cards())
  }

  test("givenAFoundationWithACard_whenMovingToATableauPileThatCannotReceiveThatCard_thenExceptionIsThrown") {
    val foundation = new Foundation(twoOfClubs :: Nil)
    val tableauPile = new TableauPile(aceOfGolds :: Nil)
    intercept[InvalidMoveException] {
      move(foundation, tableauPile)
    }
  }
}
