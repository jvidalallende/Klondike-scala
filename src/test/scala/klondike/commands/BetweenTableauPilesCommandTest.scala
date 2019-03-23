package klondike.commands

import klondike.controllers.MovementBuilder
import klondike.exceptions.{EmptyPileException, InvalidMoveException}
import klondike.models._
import klondike.views.{IOManager, SpanishGameFactory}
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite

class BetweenTableauPilesCommandTest extends FunSuite with MockFactory {

  private val kingOfGolds = new SpanishCard(SpanishCard.MAX_VALUE, "golds", true)
  private val knightOfGolds = new SpanishCard(SpanishCard.MAX_VALUE - 1, "golds", true)
  private val emptyTP = new TableauPile(Nil)
  private val emptyTableauPiles = emptyTP :: emptyTP :: emptyTP :: emptyTP :: emptyTP :: emptyTP :: emptyTP :: Nil

  private val movementBuilder = new MovementBuilder(SpanishGameFactory.tableauPileValidator)
  private val command = new BetweenTableauPilesCommand("", movementBuilder, _)

  test("givenAGameWithEmptyTableauPiles_whenMovingBetweenTableauPiles_thenExceptionIsRaised") {
    val game = new Game(new Board(new Deck(Nil), new Waste(Nil), Nil, emptyTableauPiles))
    val stubIO = stub[IOManager]
    inSequence {
      (stubIO.readInt _).when(*).returns(1).noMoreThanOnce()
      (stubIO.readInt _).when(*).returns(2)
    }
    intercept[EmptyPileException] {
      command(stubIO).execute(game)
    }
  }

  test("givenAGameWithEmptyTableauPiles_whenMovingFromOneTableauPileToTheSameOne_thenExceptionIsRaised") {
    val game = new Game(new Board(new Deck(Nil), new Waste(Nil), Nil, emptyTableauPiles))
    val stubIO = stub[IOManager]
    inSequence {
      (stubIO.readInt _).when(*).returns(1)
    }
    intercept[InvalidMoveException] {
      command(stubIO).execute(game)
    }
  }

  test("givenAGameWithTwoCardsInThirdTableauPile_whenMovingToTheFifthTableauPile_thenTheNewGameHasThatCardInTheExpectedTableauPile") {
    val tableauPiles = emptyTP :: emptyTP :: new TableauPile(knightOfGolds :: kingOfGolds :: Nil) :: emptyTP :: emptyTP :: emptyTP :: emptyTP :: Nil
    val game = new Game(new Board(new Deck(Nil), new Waste(Nil), Nil, tableauPiles))
    val stubIO = stub[IOManager]
    inSequence {
      (stubIO.readInt _).when(*).returns(3).noMoreThanOnce()
      (stubIO.readInt _).when(*).returns(5).noMoreThanOnce()
      (stubIO.readInt _).when(*).returns(2)
    }
    val expectedTableauPiles = emptyTP :: emptyTP :: emptyTP :: emptyTP :: new TableauPile(knightOfGolds :: kingOfGolds :: Nil) :: emptyTP :: emptyTP :: Nil
    val expected = new Game(new Board(new Deck(Nil), new Waste(Nil), Nil, expectedTableauPiles))
    assert(expected == command(stubIO).execute(game))
  }
}
