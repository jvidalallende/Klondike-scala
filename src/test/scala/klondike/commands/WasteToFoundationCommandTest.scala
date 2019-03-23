package klondike.commands

import klondike.controllers.MovementBuilder
import klondike.exceptions.EmptyPileException
import klondike.models._
import klondike.views.{IOManager, SpanishGameFactory}
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite

class WasteToFoundationCommandTest extends FunSuite with MockFactory {

  private val aceOfGolds = new SpanishCard(1, "golds")
  private val emptyFoundation = new Foundation(Nil)
  private val emptyFoundations = emptyFoundation :: emptyFoundation :: emptyFoundation :: emptyFoundation :: Nil

  private val movementBuilder = new MovementBuilder(SpanishGameFactory.tableauPileValidator)
  private val command = new WasteToFoundationCommand("", movementBuilder, _)

  test("givenAGameWithEmptyWaste_whenMovingFromWasteToFoundation_thenExceptionIsRaised") {
    val game = new Game(new Board(new Deck(Nil), new Waste(Nil), emptyFoundations, Nil))
    val stubIO = stub[IOManager]
    (stubIO.readInt _).when(*).returns(1)
    intercept[EmptyPileException] {
      command(stubIO).execute(game)
    }
  }

  test("givenAGameWithAOneCardInWaste_whenMovingFromWasteToFoundation_thenTheNewGameHasThatCardInTheExpectedFoundation") {
    val game = new Game(new Board(new Deck(Nil), new Waste(aceOfGolds :: Nil), emptyFoundations, Nil))
    val stubIO = stub[IOManager]
    (stubIO.readInt _).when(*).returns(3)
    val expectedFoundations = emptyFoundation :: emptyFoundation :: new Foundation(aceOfGolds :: Nil) :: emptyFoundation :: Nil
    val expected = new Game(new Board(new Deck(Nil), new Waste(Nil), expectedFoundations, Nil))
    assert(expected == command(stubIO).execute(game))
  }
}
