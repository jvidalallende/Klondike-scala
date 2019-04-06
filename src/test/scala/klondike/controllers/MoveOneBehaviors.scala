package klondike.controllers

import klondike.exceptions.{EmptyPileException, InvalidMoveException}
import klondike.models.Pile
import org.scalatest.FunSuite

trait MoveOneBehaviors {
  this: FunSuite =>

  def emptySource(cardType: String, source: Pile, destination: Pile, move: (Pile, Pile) => (Pile, Pile)) {

    test(s"givenEmpty${source.name}_whenMovingTo${destination.name}with${cardType}_thenEmptyPileExceptionIsThrown") {
      intercept[EmptyPileException] {
        move(source, destination)
      }
    }
  }

  def destinationThatCanAcceptTheCard(cardType: String, source: Pile, destination: Pile, move: (Pile, Pile) => (Pile, Pile)) {

    test(s"givenA${source.name}WithOneCard_whenMoving${source.cards.head.toString}To${destination.name}ThatCanReceiveThe${cardType}_thenNew${source.name}IsEmptyAndNew${destination.name}ContainsTheCard") {
      val (newSource, newDestination) = move(source, destination)
      assert(newSource.empty)
      assert(newDestination.cards.head == source.cards.head.upturn())
    }
  }

  def destinationThatCannotAcceptTheCard(cardType: String, source: Pile, destination: Pile, move: (Pile, Pile) => (Pile, Pile)) {

    test(s"givenA${source.name}WithOneCard_whenMovingTo${destination.name}ThatCannotReceiveThe${cardType}_thenInvalidMoveExceptionIsThrown") {
      intercept[InvalidMoveException] {
        move(source, destination)
      }
    }
  }
}
