package klondike.views

import klondike.exceptions.EmptyPileException
import klondike.models._
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite

class WasteToFoundationCommandTest extends FunSuite with MockFactory {

  private val aceOfGolds = new SpanishCard(1, "golds")
  private val emptyFoundation = new Foundation(Nil)
  private val emptyFoundations = emptyFoundation :: emptyFoundation :: emptyFoundation :: emptyFoundation :: Nil

  test("givenAGameWithEmptyWaste_whenMovingFromWasteToFoundation_thenExceptionIsRaised") {
    val game = new Game(new Board(new Deck(Nil), new Waste(Nil), emptyFoundations, Nil))
    val stubIO = mock[IOManager]
    (stubIO.readInt(_: String)).expects(*).returning(1)
    intercept[EmptyPileException] {
      Commands.wasteToFoundation(stubIO).execute(game)
    }
  }

  test("givenAGameWithAOneCardInWaste_whenMovingFromWasteToFoundation_thenTheNewGameHasThatCardInTheExpectedFoundation") {
    val game = new Game(new Board(new Deck(Nil), new Waste(aceOfGolds :: Nil), emptyFoundations, Nil))
    val stubIO = mock[IOManager]
    (stubIO.readInt(_: String)).expects(*).returning(3)
    val expectedFoundations = emptyFoundation :: emptyFoundation :: new Foundation(aceOfGolds :: Nil) :: emptyFoundation :: Nil
    val expected = new Game(new Board(new Deck(Nil), new Waste(Nil), expectedFoundations, Nil))
    assert(expected == Commands.wasteToFoundation(stubIO).execute(game))
  }
}
