package controllers

import exceptions.{EmptyPileException, InvalidMoveException}
import models.{Foundation, SpanishCard, Waste}
import org.scalatest.FunSuite

class WasteToFoundationTest extends FunSuite {

  private val emptyWaste = new Waste(Nil)
  private val emptyFoundation = new Foundation(Nil)

  private val aceOfGolds = new SpanishCard(1, "golds", true)
  private val twoOfGolds = new SpanishCard(2, "golds", true)
  private val twoOfClubs = new SpanishCard(2, "clubs", true)

  private val move = new MovementFactory().wasteToFoundation()

  test("givenEmptyWasteAndFoundation_whenMovingFromWasteToFoundation_thenExceptionIsThrown") {
    intercept[EmptyPileException] {
      move(emptyWaste, emptyFoundation)
    }
  }

  test("givenAWasteWithAnAce_whenMovingToAnEmptyFoundation_thenNewWasteIsEmptyAndFoundationContainsTheCard") {
    val waste = new Waste(aceOfGolds :: Nil)
    val result = move(waste, emptyFoundation)
    assert(result._1.empty())
    assert(aceOfGolds :: Nil == result._2.cards())
  }

  test("givenAWasteWithACardDifferentFromAnAce_whenMovingToAnEmptyFoundation_thenExceptionIsThrown") {
    val waste = new Waste(twoOfGolds :: Nil)
    intercept[InvalidMoveException] {
      move(waste, emptyFoundation)
    }
  }

  test("givenAWasteWithOneCard_whenMovingToAFoundationThatCanReceiveThatCard_thenNewWasteIsEmptyAndFoundationsContainsTheCards") {
    val waste = new Waste(twoOfGolds :: Nil)
    val foundation = new Foundation(aceOfGolds :: Nil)
    val result = move(waste, foundation)
    assert(result._1.empty())
    assert(twoOfGolds :: foundation.cards() == result._2.cards())
  }

  test("givenAWasteWithACard_whenMovingToAFoundationThatCannotReceiveThatCard_thenExceptionIsThrown") {
    val waste = new Waste(twoOfClubs :: Nil)
    val foundation = new Foundation(aceOfGolds :: Nil)
    intercept[InvalidMoveException] {
      move(waste, foundation)
    }
  }
}
