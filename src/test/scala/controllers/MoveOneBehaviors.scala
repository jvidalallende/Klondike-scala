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

    test(s"givenA${source.name}WithOneCard_whenMovingToAnEmpty${destination.name}ThatCanReceiveTheCard_thenNew${source.name}IsEmptyAndNew${destination.name}ContainsTheCard") {
      val (newSource, newDestination) = move(source, destination)
      assert(newSource.empty())
      assert(newDestination.cards().head == source.cards().head.upturn())
    }
  }

  def emptyDestinationThatCannotAcceptTheCard(source: Pile, destination: Pile, move: (Pile, Pile) => (Pile, Pile)) {

    test(s"givenA${source.name}WithOneCard_whenMovingToAnEmpty${destination.name}ThatCannotReceiveTheCard_thenInvalidMoveExceptionIsThrown") {
      intercept[InvalidMoveException] {
        move(source, destination)
      }
    }
  }

  def nonEmptyDestinationThatCanAcceptTheCard(source: Pile, destination: Pile, move: (Pile, Pile) => (Pile, Pile)) {

    test(s"givenA${source.name}WithOneCard_whenMovingToA${destination.name}ThatCanReceiveTheCard_thenNew${source.name}IsEmptyAndNew${destination.name}ContainsTheCardOnTopOfItsPreviousCards") {
      val (newSource, newDestination) = move(source, destination)
      assert(newSource.empty())
      assert(newDestination.cards() == source.cards().head.upturn() :: destination.cards())
    }
  }

  def nonEmptyDestinationThatCannotAcceptTheCard(source: Pile, destination: Pile, move: (Pile, Pile) => (Pile, Pile)) {

    test(s"givenA${source.name}WithOneCard_whenMovingToA${destination.name}ThatCannotReceiveTheCard_thenInvalidMoveExceptionIsThrown") {
      intercept[InvalidMoveException] {
        move(source, destination)
      }
    }
  }
}