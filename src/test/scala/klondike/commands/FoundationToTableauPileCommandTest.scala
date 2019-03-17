package klondike.commands

import klondike.exceptions.EmptyPileException
import klondike.models._
import klondike.views.IOManager
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite

class FoundationToTableauPileCommandTest extends FunSuite with MockFactory {

  private val kingOfGolds = new SpanishCard(SpanishCard.MAX_VALUE, "golds", true)
  private val emptyFoundation = new Foundation(Nil)
  private val emptyFoundations = emptyFoundation :: emptyFoundation :: emptyFoundation :: emptyFoundation :: Nil
  private val emptyTP = new TableauPile(Nil)
  private val emptyTableauPiles = emptyTP :: emptyTP :: emptyTP :: emptyTP :: emptyTP :: emptyTP :: emptyTP :: Nil

  test("givenAGameWithEmptyFoundations_whenMovingFromFoundationToTableauPile_thenExceptionIsRaised") {
    val game = new Game(new Board(new Deck(Nil), new Waste(Nil), emptyFoundations, emptyTableauPiles))
    val stubIO = stub[IOManager]
    inSequence {
      (stubIO.readInt _).when(*).returns(1).noMoreThanOnce()
      (stubIO.readInt _).when(*).returns(2)
    }
    intercept[EmptyPileException] {
      Commands.foundationToTableauPile(stubIO).execute(game)
    }
  }

  test("givenAGameWithOneCardInTheFirstFoundation_whenMovingFromFoundationToTableauPile_thenTheNewGameHasThatCardInTheExpectedTableauPile") {
    val foundations = new Foundation(kingOfGolds :: Nil) :: emptyFoundation :: emptyFoundation :: emptyFoundation :: Nil
    val game = new Game(new Board(new Deck(Nil), new Waste(Nil), foundations, emptyTableauPiles))
    val stubIO = stub[IOManager]
    inSequence {
      (stubIO.readInt _).when(*).returns(1).noMoreThanOnce()
      (stubIO.readInt _).when(*).returns(4)
    }
    val expectedTableauPiles = emptyTP :: emptyTP :: emptyTP :: new TableauPile(kingOfGolds :: Nil) :: emptyTP :: emptyTP :: emptyTP :: Nil
    val expected = new Game(new Board(new Deck(Nil), new Waste(Nil), emptyFoundations, expectedTableauPiles))
    assert(expected == Commands.foundationToTableauPile(stubIO).execute(game))
  }
}