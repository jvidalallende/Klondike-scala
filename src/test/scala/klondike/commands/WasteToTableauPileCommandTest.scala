package klondike.commands

import klondike.controllers.MovementFactory
import klondike.exceptions.EmptyPileException
import klondike.models._
import klondike.views.{IOManager, SpanishGameFactory}
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite

class WasteToTableauPileCommandTest extends FunSuite with MockFactory {

  private val kingOfGolds = new SpanishCard(SpanishCard.MAX_VALUE, "golds", true)
  private val emptyTP = new TableauPile(Nil)
  private val emptyTableauPiles = emptyTP :: emptyTP :: emptyTP :: emptyTP :: emptyTP :: emptyTP :: emptyTP :: Nil

  private val movementFactory = new MovementFactory(SpanishGameFactory.tableauPileValidator)
  private val command = new WasteToTableauPileCommand("", movementFactory, _)

  test("givenABoardWithEmptyWaste_whenMovingFromWasteToTableauPile_thenExceptionIsRaised") {
    val board = new Board(new Deck(Nil), new Waste(Nil), Nil, emptyTableauPiles)
    val stubIO = stub[IOManager]
    (stubIO.readInt _).when(*).returns(1)
    intercept[EmptyPileException] {
      command(stubIO).execute(board)
    }
  }

  test("givenABoardWithAOneCardInWaste_whenMovingFromWasteToTableauPile_thenTheNewBoardHasThatCardInTheExpectedTableauPile") {
    val board = new Board(new Deck(Nil), new Waste(kingOfGolds :: Nil), Nil, emptyTableauPiles)
    val stubIO = stub[IOManager]
    (stubIO.readInt _).when(*).returns(5)
    val expectedTableauPiles = emptyTP :: emptyTP :: emptyTP :: emptyTP :: new TableauPile(kingOfGolds :: Nil) :: emptyTP :: emptyTP :: Nil
    val expected = new Board(new Deck(Nil), new Waste(Nil), Nil, expectedTableauPiles)
    assert(expected == command(stubIO).execute(board))
  }
}
