package klondike.controllers

import klondike.exceptions.{EmptyPileException, InvalidMoveException}
import klondike.models.Pile
import org.scalatest.FunSuite

trait MoveOneBehaviors {
  this: FunSuite =>

  def emptySource[A, B](source: Pile[A], destination: Pile[B], move: (Pile[A], Pile[B]) => (Pile[A], Pile[B])) {

    test(s"givenEmpty${source.name}_whenMovingTo${destination.name}_thenEmptyPileExceptionIsThrown") {
      intercept[EmptyPileException] {
        move(source, destination)
      }
    }
  }

  def destinationThatCanAcceptTheCard[A, B](source: Pile[A], destination: Pile[B], move: (Pile[A], Pile[B]) => (Pile[A], Pile[B])) {

    test(s"givenA${source.name}WithOneCard_whenMovingTo${destination.name}ThatCanReceiveTheCard_thenNew${source.name}IsEmptyAndNew${destination.name}ContainsTheCard") {
      val (newSource, newDestination) = move(source, destination)
      assert(newSource.empty)
      assert(newDestination.cards.head == source.cards.head.upturn())
    }
  }

  def destinationThatCannotAcceptTheCard[A, B](source: Pile[A], destination: Pile[B], move: (Pile[A], Pile[B]) => (Pile[A], Pile[B])) {

    test(s"givenA${source.name}WithOneCard_whenMovingTo${destination.name}ThatCannotReceiveTheCard_thenInvalidMoveExceptionIsThrown") {
      intercept[InvalidMoveException] {
        move(source, destination)
      }
    }
  }
}