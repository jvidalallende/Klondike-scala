package klondike.views

import klondike.models.Board
import klondike.test_utils.TestModels._
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite
import org.scalatest.time.SpanSugar._
import org.scalatest.concurrent.Timeouts._

class MainMenuTest extends FunSuite with MainMenuBehaviors with MockFactory {

  testsFor(mainMenuTests(SpanishGameFactory))


  test("givenAMainMenuWithASpanishGame_whenExecutingTheMovementThatFillsFoundations_thenExecutionsEndsWithoutTimeout") {
    val foundations = List(kingOfGolds, knightOfCups, kingOfSwords, kingOfClubs).map(c => foundationWithCard(c))
    val boardAlmostFinished = new Board(emptyDeck, wasteWithCard(kingOfCups), foundations, emptyTableauPiles)
    val gameFactoryMock = mock[GameFactory]
    val mockIO = mock[IOManager]
    (gameFactoryMock.initialBoard _).expects(7).returning(boardAlmostFinished).once()
    (gameFactoryMock.cardView _).expects().returning(SpanishGameFactory.cardView)
    (gameFactoryMock.tableauPileValidator _).expects().returning(SpanishGameFactory.tableauPileValidator)
    (mockIO.write(_: String)).expects(*).anyNumberOfTimes()
    (mockIO.readInt _).expects(*).returning(2).noMoreThanOnce() // Waste to Foundation
    (mockIO.readInt _).expects(*).returning(2).noMoreThanOnce() // Destination foundation
    val menu = MainMenuBuilder.build(gameFactoryMock, mockIO)
    failAfter(200 millis) {
      menu.run()
    }
  }

}
