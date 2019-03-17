package klondike.commands

import klondike.exceptions.EmptyPileException
import klondike.models._
import klondike.views.IOManager
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite

class TableauPileToFoundationCommandTest extends FunSuite with MockFactory {

  private val aceOfGolds = new SpanishCard(1, "golds", true)
  private val emptyFoundation = new Foundation(Nil)
  private val emptyFoundations = emptyFoundation :: emptyFoundation :: emptyFoundation :: emptyFoundation :: Nil
  private val emptyTP = new TableauPile(Nil)
  private val emptyTableauPiles = emptyTP :: emptyTP :: emptyTP :: emptyTP :: emptyTP :: emptyTP :: emptyTP :: Nil

  test("givenAGameWithEmptyTableauPiles_whenMovingFromTableauPileToFoundation_thenExceptionIsRaised") {
    val game = new Game(new Board(new Deck(Nil), new Waste(Nil), emptyFoundations, emptyTableauPiles))
    val stubIO = stub[IOManager]
    inSequence {
      (stubIO.readInt _).when(*).returns(1).noMoreThanOnce()
      (stubIO.readInt _).when(*).returns(2)
    }
    intercept[EmptyPileException] {
      Commands.tableauPileToFoundation(stubIO).execute(game)
    }
  }

  test("givenAGameWithOneCardInThirdTableauPile_whenMovingFromTableauPileToFoundation_thenTheNewGameHasThatCardInTheExpectedFoundation") {
    val tableauPiles = emptyTP :: emptyTP :: new TableauPile(aceOfGolds :: Nil) :: emptyTP :: emptyTP :: emptyTP :: emptyTP :: Nil
    val game = new Game(new Board(new Deck(Nil), new Waste(Nil), emptyFoundations, tableauPiles))
    val stubIO = stub[IOManager]
    inSequence {
      (stubIO.readInt _).when(*).returns(3).noMoreThanOnce()
      (stubIO.readInt _).when(*).returns(1)
    }
    val expectedFoundations = new Foundation(aceOfGolds :: Nil) :: emptyFoundation :: emptyFoundation :: emptyFoundation :: Nil
    val expected = new Game(new Board(new Deck(Nil), new Waste(Nil), expectedFoundations, emptyTableauPiles))
    assert(expected == Commands.tableauPileToFoundation(stubIO).execute(game))
  }
}
