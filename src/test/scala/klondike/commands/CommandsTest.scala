package klondike.commands

import klondike.controllers.MovementFactory
import klondike.exceptions.{ExitGameException, InvalidMoveException}
import klondike.models.TableauPile
import klondike.test_utils.IOStubs
import klondike.test_utils.TestModels._
import klondike.views.SpanishGameFactory
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite

class CommandsTest extends FunSuite with MockFactory with CommandBehaviors {

  private val movementFactory = new MovementFactory(SpanishGameFactory.tableauPileValidator)
  private val hitDeckCommand = new HitDeckCommand("HitDeckCommand", movementFactory)
  private val wasteToFoundation = new WasteToFoundationCommand("WasteToFoundationCommand", movementFactory, _)
  private val wasteToTableauPile = new WasteToTableauPileCommand("WasteToTableauPileCommand", movementFactory, _)
  private val foundationToTableauPile = new FoundationToTableauPileCommand("FoundationToTableauPileCommand", movementFactory, _)
  private val tableauPileToFoundation = new TableauPileToFoundationCommand("TableauPileToFoundationCommand", movementFactory, _)
  private val betweenTableauPiles = new BetweenTableauPilesCommand("BetweenTableauPilesCommand", movementFactory, _)

  // Hit Deck
  testsFor(emptySource(hitDeckCommand))

  testsFor(validMove(
    "givenABoardWithAOneCardDeck_whenDoingHitDeckMovement_thenTheNewBoardHasThatCardInTheWaste",
    hitDeckCommand,
    emptyBoardWithDeck(deckWithCard(aceOfGolds)),
    emptyBoardWithWaste(wasteWithCard(aceOfGolds))
  ))


  // Waste --> Foundation
  testsFor(emptySource(wasteToFoundation(IOStubs.readInt(Seq(1)))))

  testsFor(validMove(
    "givenABoardWithAOneCardInWaste_whenMovingFromWasteToFoundation_thenTheNewBoardHasThatCardInTheExpectedFoundation",
    wasteToFoundation(IOStubs.readInt(Seq(1))),
    emptyBoardWithWaste(wasteWithCard(aceOfGolds)),
    emptyBoardWithFoundation(foundationWithCard(aceOfGolds), 0)
  ))


  // Waste --> TableauPile
  testsFor(emptySource(wasteToTableauPile(IOStubs.readInt(Seq(7)))))

  testsFor(validMove(
    "givenABoardWithAOneCardInWaste_whenMovingFromWasteToTableauPile_thenTheNewBoardHasThatCardInTheExpectedTableauPile",
    wasteToTableauPile(IOStubs.readInt(Seq(7))),
    emptyBoardWithWaste(wasteWithCard(kingOfGolds)),
    emptyBoardWithTableauPile(tableauPileWithCard(kingOfGolds), 6)
  ))


  // Foundation --> TableauPile
  testsFor(emptySource(foundationToTableauPile(IOStubs.readInt(Seq(1, 1)))))

  testsFor(validMove(
    "givenABoardWithOneCardInTheFirstFoundation_whenMovingFromFoundationToTableauPile_thenTheNewBoardHasThatCardInTheExpectedTableauPile",
    foundationToTableauPile(IOStubs.readInt(Seq(1, 4))),
    emptyBoardWithFoundation(foundationWithCard(kingOfGolds), 0),
    emptyBoardWithTableauPile(tableauPileWithCard(kingOfGolds), 3)
  ))


  // TableauPile --> Foundation
  testsFor(emptySource(tableauPileToFoundation(IOStubs.readInt(Seq(1, 1)))))

  testsFor(validMove(
    "givenABoardWithOneCardInThirdTableauPile_whenMovingFromTableauPileToFoundation_thenTheNewBoardHasThatCardInTheExpectedFoundation",
    tableauPileToFoundation(IOStubs.readInt(Seq(3, 4))),
    emptyBoardWithTableauPile(tableauPileWithCard(aceOfGolds), 2),
    emptyBoardWithFoundation(foundationWithCard(aceOfGolds), 3)
  ))


  // TableauPile --> TableauPile
  testsFor(emptySource(betweenTableauPiles(IOStubs.readInt(Seq(1, 2, 1)))))

  test("givenABoardWithEmptyTableauPiles_whenMovingFromOneTableauPileToTheSameOne_thenExceptionIsRaised") {
    val board = emptyBoard
    intercept[InvalidMoveException] {
      betweenTableauPiles(IOStubs.readInt(Seq(5, 5))).execute(board)
    }
  }

  testsFor(validMove(
    "givenABoardWithTwoCardsInThirdTableauPile_whenMovingToTheFifthTableauPile_thenTheNewBoardHasThatCardInTheExpectedTableauPile",
    betweenTableauPiles(IOStubs.readInt(Seq(3, 5, 2))),
    emptyBoardWithTableauPile(new TableauPile(knightOfCups :: kingOfGolds :: Nil), 2),
    emptyBoardWithTableauPile(new TableauPile(knightOfCups :: kingOfGolds :: Nil), 4)
  ))


  // Exit Command
  test("givenABoard_whenExecutingExitCommand_thenExitExceptionIsRaised") {
    intercept[ExitGameException] {
      new ExitCommand("").execute(emptyBoard)
    }
  }
}
