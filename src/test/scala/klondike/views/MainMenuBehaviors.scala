package klondike.views

import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite
import org.scalatest.time.SpanSugar._
import org.scalatest.concurrent.Timeouts._

trait MainMenuBehaviors extends MockFactory {
  this: FunSuite =>

  def mainMenuTests(gameFactory: GameFactory) {

    test(s"givenAMenuBuilderWith${gameFactory.name}_whenCreatingTheMenuAndSelectingTheExitOption_thenTheTestsEndsWithoutTimeout") {
      val mockIO = mock[IOManager]
      (mockIO.write(_: String)).expects(*).anyNumberOfTimes()
      (mockIO.readInt _).expects(*).returning(7)
      val menu = MainMenuBuilder.build(gameFactory, mockIO)
      failAfter(200 millis) {
        menu.run()
      }
    }

    test(s"givenAMenuBuilderWith${gameFactory.name}_whenCreatingTheMenuHittingDeckAndSelectingTheExitOption_thenTheTestsEndsWithoutTimeout") {
      val mockIO = mock[IOManager]
      (mockIO.write(_: String)).expects(*).anyNumberOfTimes()
      (mockIO.readInt _).expects(*).returning(1).noMoreThanOnce() // Hit Deck
      (mockIO.readInt _).expects(*).returning(7) // Exit
      val menu = MainMenuBuilder.build(gameFactory, mockIO)
      failAfter(200 millis) {
        menu.run()
      }
    }

    test(s"givenAMenuBuilderWith${gameFactory.name}_whenCreatingTheMenuMovingFromWasteToFoundationAndSelectingTheExitOption_thenTheTestsEndsWithoutTimeout") {
      val mockIO = mock[IOManager]
      (mockIO.write(_: String)).expects(*).anyNumberOfTimes()
      (mockIO.readInt _).expects(*).returning(2).noMoreThanOnce() // Waste to Foundation
      (mockIO.readInt _).expects(*).returning(1).noMoreThanOnce() // Destination for the foundation
      (mockIO.readInt _).expects(*).returning(7) // Exit
      val menu = MainMenuBuilder.build(gameFactory, mockIO)
      failAfter(200 millis) {
        menu.run()
      }
    }
  }
}
