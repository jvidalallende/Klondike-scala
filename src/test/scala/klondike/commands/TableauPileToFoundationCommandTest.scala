package klondike.commands

import klondike.controllers.MovementFactory
import klondike.exceptions.EmptyPileException
import klondike.models._
import klondike.views.{IOManager, SpanishGameFactory}
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite

class TableauPileToFoundationCommandTest extends FunSuite with MockFactory {

  private val aceOfGolds = new SpanishCard(1, "golds", true)
  private val emptyFoundation = new Foundation(Nil)
  private val emptyFoundations = emptyFoundation :: emptyFoundation :: emptyFoundation :: emptyFoundation :: Nil
  private val emptyTP = new TableauPile(Nil)
  private val emptyTableauPiles = emptyTP :: emptyTP :: emptyTP :: emptyTP :: emptyTP :: emptyTP :: emptyTP :: Nil

  private val movementFactory = new MovementFactory(SpanishGameFactory.tableauPileValidator)
  private val command = new TableauPileToFoundationCommand("", movementFactory, _)

  test("givenABoardWithEmptyTableauPiles_whenMovingFromTableauPileToFoundation_thenExceptionIsRaised") {
    val board = new Board(new Deck(Nil), new Waste(Nil), emptyFoundations, emptyTableauPiles)
    val stubIO = stub[IOManager]
    inSequence {
      (stubIO.readInt _).when(*).returns(1).noMoreThanOnce()
      (stubIO.readInt _).when(*).returns(2)
    }
    intercept[EmptyPileException] {
      command(stubIO).execute(board)
    }
  }

  test("givenABoardWithOneCardInThirdTableauPile_whenMovingFromTableauPileToFoundation_thenTheNewBoardHasThatCardInTheExpectedFoundation") {
    val tableauPiles = emptyTP :: emptyTP :: new TableauPile(aceOfGolds :: Nil) :: emptyTP :: emptyTP :: emptyTP :: emptyTP :: Nil
    val board = new Board(new Deck(Nil), new Waste(Nil), emptyFoundations, tableauPiles)
    val stubIO = stub[IOManager]
    inSequence {
      (stubIO.readInt _).when(*).returns(3).noMoreThanOnce()
      (stubIO.readInt _).when(*).returns(1)
    }
    val expectedFoundations = new Foundation(aceOfGolds :: Nil) :: emptyFoundation :: emptyFoundation :: emptyFoundation :: Nil
    val expected = new Board(new Deck(Nil), new Waste(Nil), expectedFoundations, emptyTableauPiles)
    assert(expected == command(stubIO).execute(board))
  }
}
