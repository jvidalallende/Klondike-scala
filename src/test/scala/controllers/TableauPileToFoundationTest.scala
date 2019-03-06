package controllers

import exceptions.{EmptyPileException, InvalidMoveException}
import models.{Foundation, SpanishCard, TableauPile}
import org.scalatest.FunSuite

class TableauPileToFoundationTest extends FunSuite {

  private val emptyTableauPile = new TableauPile(Nil)
  private val emptyFoundation = new Foundation(Nil)

  private val aceOfGolds = new SpanishCard(1, "golds", true)
  private val twoOfGolds = new SpanishCard(2, "golds", true)
  private val twoOfClubs = new SpanishCard(2, "clubs", true)

  private val move = new MovementFactory().tableauPileToFoundation()

  test("givenEmptyTableauPileAndFoundation_whenMovingFromTableauPileToFoundation_thenExceptionIsThrown") {
    intercept[EmptyPileException] {
      move(emptyTableauPile, emptyFoundation)
    }
  }

  test("givenATableauPileWithAnAce_whenMovingToAnEmptyFoundation_thenNewTableauPileIsEmptyAndFoundationContainsTheCard") {
    val tableauPile = new TableauPile(aceOfGolds :: Nil)
    val result = move(tableauPile, emptyFoundation)
    assert(result._1.empty())
    assert(aceOfGolds :: Nil == result._2.cards())
  }

  test("givenATableauPileWithACardDifferentFromAnAce_whenMovingToAnEmptyFoundation_thenExceptionIsThrown") {
    val tableauPile = new TableauPile(twoOfGolds :: Nil)
    intercept[InvalidMoveException] {
      move(tableauPile, emptyFoundation)
    }
  }

  test("givenATableauPileWithOneCard_whenMovingToAFoundationThatCanReceiveThatCard_thenNewTableauPileIsEmptyAndFoundationsContainsTheCards") {
    val tableauPile = new TableauPile(twoOfGolds :: Nil)
    val foundation = new Foundation(aceOfGolds :: Nil)
    val result = move(tableauPile, foundation)
    assert(result._1.empty())
    assert(twoOfGolds :: foundation.cards() == result._2.cards())
  }

  test("givenATableauPileWithACard_whenMovingToAFoundationThatCannotReceiveThatCard_thenExceptionIsThrown") {
    val tableauPile = new TableauPile(twoOfClubs :: Nil)
    val foundation = new Foundation(aceOfGolds :: Nil)
    intercept[InvalidMoveException] {
      move(tableauPile, foundation)
    }
  }
}
