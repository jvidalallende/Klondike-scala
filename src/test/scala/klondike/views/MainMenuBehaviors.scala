package klondike.views

import klondike.io.IOManager
import klondike.test_utils.TestModels._
import klondike.models.{Board, Card, Foundation}
import klondike.test_utils.IOMocks
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite
import org.scalatest.time.SpanSugar._
import org.scalatest.concurrent.Timeouts._

trait MainMenuBehaviors extends MockFactory {
  this: FunSuite =>

  def mainMenuTests(gameFactory: GameFactory) {

    test(s"givenA${gameFactory.name}_whenCreatingTheMenuAndSelectingTheExitOption_thenTheTestsEndsWithoutTimeout") {
      failAfter(200 millis) {
        MainMenuBuilder.build(gameFactory, IOMocks.readIntAllowingWrites(Seq(7))).run()
      }
    }

    test(s"givenA${gameFactory.name}_whenCreatingTheMenuHittingDeckAndSelectingTheExitOption_thenTheTestsEndsWithoutTimeout") {
      failAfter(200 millis) {
        // 1: Hit Deck, 7: Exit
        MainMenuBuilder.build(gameFactory, IOMocks.readIntAllowingWrites(Seq(1, 7))).run()
      }
    }

    test(s"givenA${gameFactory.name}_whenCreatingTheMenuMovingFromWasteToFoundationAndSelectingTheExitOption_thenTheTestsEndsWithoutTimeout") {
      // 2: Waste to Foundation, 1: Destination Foundation, 7: Exit
      failAfter(200 millis) {
        MainMenuBuilder.build(gameFactory, IOMocks.readIntAllowingWrites(Seq(2, 1, 7))).run()
      }
    }

    test(s"givenA${gameFactory.name}_whenCreatingTheMenuSelectingACoupleOfInvalidOptionsAndThenTheExitOption_thenTheTestsEndsWithoutTimeout") {
      // 0: Invalid option, 23: Invalid option, 7: Exit
      failAfter(200 millis) {
        MainMenuBuilder.build(gameFactory, IOMocks.readIntAllowingWrites(Seq(0, 23, 7))).run()
      }
    }

    test(s"givenA${gameFactory.name}_whenCreatingTheMenuIntroducingAStringInsteadOfNumberThenTheExitOption_thenTheTestsEndsWithoutTimeout") {
      val mockIO = mock[IOManager]
      inSequence {
        (mockIO.readInt _).expects(*).throwing(new NumberFormatException("aaa is not a number")).once()
        (mockIO.readInt _).expects(*).returning(7).once()
      }
      (mockIO.write _).expects(*).anyNumberOfTimes()
      val menu = MainMenuBuilder.build(gameFactory, mockIO)
      failAfter(200 millis) {
        menu.run()
      }
    }
  }

  def finishGame(gameFactory: GameFactory, foundations: List[Foundation], card: Card) {

    test(s"givenA${gameFactory.name}_whenExecutingTheMovementThatFillsFoundations_thenExecutionsEndsWithoutTimeout") {
      val boardAlmostFinished = new Board(emptyDeck, wasteWithCard(card), foundations, emptyTableauPiles)
      val gameFactoryMock = mock[GameFactory]
      val mockIO = mock[IOManager]
      (gameFactoryMock.initialBoard _).expects(7).returning(boardAlmostFinished).once()
      (gameFactoryMock.cardView _).expects().returning(gameFactory.cardView)
      (gameFactoryMock.tableauPileValidator _).expects().returning(gameFactory.tableauPileValidator)
      (mockIO.write(_: String)).expects(*).anyNumberOfTimes()
      (mockIO.readInt _).expects(*).returning(2).noMoreThanOnce() // Waste to Foundation
      (mockIO.readInt _).expects(*).returning(2).noMoreThanOnce() // Destination foundation
      val menu = MainMenuBuilder.build(gameFactoryMock, mockIO)
      failAfter(200 millis) {
        menu.run()
      }
    }
  }
}
