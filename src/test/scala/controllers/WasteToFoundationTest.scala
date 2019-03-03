package controllers

import exceptions.{EmptyPileException, InvalidMoveException}
import models.{Foundation, SpanishCard, Waste}
import org.scalatest.FunSuite

class WasteToFoundationTest extends FunSuite {

  private val emptyWaste = new Waste(Nil)
  private val emptyFoundation = new Foundation(Nil)

  private val aceOfGolds = new SpanishCard(1, "golds")
  private val twoOfGolds = new SpanishCard(2, "golds")
  private val twoOfClubs = new SpanishCard(2, "clubs")

  private val movements = new MovementFactory()

  test("givenEmptyWasteAndFoundation_whenMovingFromWasteToFoundation_thenExceptionIsThrown") {
    intercept[EmptyPileException] {
      movements.wasteToFoundation()(emptyWaste, emptyFoundation, 1)
    }
  }

  test("givenAWasteWithAnAce_whenMovingToAnEmptyFoundation_thenNewWasteIsEmptyAndFoundationContainsTheCard") {
    val waste = new Waste(aceOfGolds :: Nil)
    val result = movements.wasteToFoundation()(waste, emptyFoundation, 1)
    assert(result._1.empty())
    assert(aceOfGolds.upturn() :: Nil == result._2.cards())
  }

  test("givenAWasteWithACardDifferentFromAnAce_whenMovingToAnEmptyFoundation_thenExceptionIsThrown") {
    val waste = new Waste(twoOfGolds :: Nil)
    intercept[InvalidMoveException] {
      movements.wasteToFoundation()(waste, emptyFoundation, 1)
    }
  }

  test("givenAWasteWithOneCard_whenMovingToAFoundationThatCanReceiveThatCard_thenNewWasteIsEmptyAndFoundationsContainsTheCards") {
    val waste = new Waste(twoOfGolds :: Nil)
    val foundation = new Foundation(aceOfGolds.upturn() :: Nil)
    val result = movements.wasteToFoundation()(waste, foundation, 1)
    assert(result._1.empty())
    assert(twoOfGolds.upturn() :: foundation.cards() == result._2.cards())
  }

  test("givenAWasteWithACard_whenMovingToAFoundationThatCannotReceiveThatCard_thenExceptionIsThrown") {
    val waste = new Waste(twoOfClubs :: Nil)
    val foundation = new Foundation(aceOfGolds.upturn() :: Nil)
    intercept[InvalidMoveException] {
      movements.wasteToFoundation()(waste, foundation, 1)
    }
  }
}
