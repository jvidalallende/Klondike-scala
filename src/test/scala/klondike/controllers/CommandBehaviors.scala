package klondike.controllers

import klondike.exceptions.EmptyPileException
import klondike.models.Board
import klondike.test_utils.TestModels
import org.scalatest.FunSuite

trait CommandBehaviors {
  this: FunSuite =>

  def emptySource(command: Command) {
    test(s"givenABoardWithEmptyFoundations_whenExecuting${command.title}_thenExceptionIsRaised") {
      intercept[EmptyPileException] {
        command.execute(TestModels.emptyBoard)
      }
    }
  }

  def validMove(testName: String, command: Command, original: Board, expected: Board) {
    test(testName) {
      assert(expected == command.execute(original))
    }
  }
}
