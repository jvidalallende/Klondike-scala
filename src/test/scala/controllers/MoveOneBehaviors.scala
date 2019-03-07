package controllers

import exceptions.{EmptyPileException, InvalidMoveException}
import models.Pile
import org.scalatest.FunSuite

trait MoveOneBehaviors {
  this: FunSuite =>

  def emptySource(source: Pile, destination: Pile, move: (Pile, Pile) => (Pile, Pile)) {

    test(s"givenEmpty${source.name}_whenMovingTo${destination.name}_thenEmptyPileExceptionIsThrown") {
      intercept[EmptyPileException] {
        move(source, destination)
      }
    }
  }

  def emptyDestinationThatCanAcceptTheCard(source: Pile, destination: Pile, move: (Pile, Pile) => (Pile, Pile)) {

    test(
      s"""givenA${source.name}WithOneCard_
         |whenMovingToAnEmpty${destination.name}ThatCanReceiveTheCard_
         |thenNew${source.name}IsEmptyAndNew${destination.name}ContainsTheCard""".stripMargin) {

      val (newSource, newDestination) = move(source, destination)
      assert(newSource.empty())
      assert(newDestination.cards().head == source.cards().head.upturn())
    }
  }

  def emptyDestinationThatCannotAcceptTheCard(source: Pile, destination: Pile, move: (Pile, Pile) => (Pile, Pile)) {

    test(
      s"""givenA${source.name}WithOneCard_
         |whenMovingToAnEmpty${destination.name}ThatCannotReceiveTheCard_
         |thenInvalidMoveExceptionIsThrown""".stripMargin) {

      intercept[InvalidMoveException] {
        move(source, destination)
      }
    }
  }

  def nonEmptyDestinationThatCanAcceptTheCard(source: Pile, destination: Pile, move: (Pile, Pile) => (Pile, Pile)) {

    test(
      s"""givenA${source.name}WithOneCard_
         |whenMovingToA${destination.name}ThatCanReceiveTheCard_
         |thenNew${source.name}IsEmptyAndNew${destination.name}ContainsTheCardOnTopOfItsPreviousCards""".stripMargin) {

      val (newSource, newDestination) = move(source, destination)
      assert(newSource.empty())
      assert(newDestination.cards() == source.cards().head.upturn() :: destination.cards())
    }
  }

  def nonEmptyDestinationThatCannotAcceptTheCard(source: Pile, destination: Pile, move: (Pile, Pile) => (Pile, Pile)) {

    test(
      s"""givenA${source.name}WithOneCard_
         |whenMovingToA${destination.name}ThatCannotReceiveTheCard_
         |thenInvalidMoveExceptionIsThrown""".stripMargin) {

      intercept[InvalidMoveException] {
        move(source, destination)
      }
    }
  }
}