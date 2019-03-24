package klondike.views

import klondike.test_utils.IOMocks
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite
import org.scalatest.time.SpanSugar._
import org.scalatest.concurrent.Timeouts._

trait MainMenuBehaviors extends MockFactory {
  this: FunSuite =>

  def mainMenuTests(gameFactory: GameFactory) {

    test(s"givenAMenuBuilderWith${gameFactory.name}_whenCreatingTheMenuAndSelectingTheExitOption_thenTheTestsEndsWithoutTimeout") {
      val mockIO = IOMocks.readIntAllowingWrites(Seq(7))
      val menu = MainMenuBuilder.build(gameFactory, mockIO)
      failAfter(200 millis) {
        menu.run()
      }
    }

    test(s"givenAMenuBuilderWith${gameFactory.name}_whenCreatingTheMenuHittingDeckAndSelectingTheExitOption_thenTheTestsEndsWithoutTimeout") {
      // 1: Hit Deck, 7: Exit
      val mockIO = IOMocks.readIntAllowingWrites(Seq(1, 7))
      val menu = MainMenuBuilder.build(gameFactory, mockIO)
      failAfter(200 millis) {
        menu.run()
      }
    }

    test(s"givenAMenuBuilderWith${gameFactory.name}_whenCreatingTheMenuMovingFromWasteToFoundationAndSelectingTheExitOption_thenTheTestsEndsWithoutTimeout") {
      // 2: Waste to Foundation, 1: Destination Foundation, 7: Exit
      val mockIO = IOMocks.readIntAllowingWrites(Seq(2, 1, 7))
      val menu = MainMenuBuilder.build(gameFactory, mockIO)
      failAfter(200 millis) {
        menu.run()
      }
    }

    test(s"givenAMenuBuilderWith${gameFactory.name}_whenCreatingTheMenuSelectingACoupleOfInvalidOptionsAndThenTheExitOption_thenTheTestsEndsWithoutTimeout") {
      // 0: Invalid option, 23: Invalid option, 7: Exit
      val mockIO = IOMocks.readIntAllowingWrites(Seq(2, 1, 7))
      val menu = MainMenuBuilder.build(gameFactory, mockIO)
      failAfter(200 millis) {
        menu.run()
      }
    }
  }
}
