package klondike.views

import klondike.io.IOManager
import klondike.test_utils.IOMocks
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite
import org.scalatest.concurrent.Timeouts._
import org.scalatest.time.SpanSugar._

class SetupMenuTest extends FunSuite with MockFactory {

  test(s"givenASetupMenu_whenPlayingSpanishGameAndExiting_thenTheTestsEndsWithoutTimeout") {
    // 1: Spanish Game 7: Exit Game 3: Exit
    failAfter(300 millis) {
      new SetupMenu(IOMocks.readIntAllowingWrites(Seq(1, 7, 3))).run()
    }
  }

  test(s"givenASetupMenu_whenPlayingFrenchGameAndExiting_thenTheTestsEndsWithoutTimeout") {
    // 2: French Game 7: Exit Game 3: Exit
    failAfter(300 millis) {
      new SetupMenu(IOMocks.readIntAllowingWrites(Seq(2, 7, 3))).run()
    }
  }

  test(s"givenASetupMenu_whenIntroducingAStringInsteadOfNumber_thenTheTestsEndsWithoutTimeout") {
    val mockIO = mock[IOManager]
    inSequence {
      (mockIO.readInt _).expects(*).throwing(new NumberFormatException("aaa is not a number")).once()
    }
    (mockIO.write _).expects(*).anyNumberOfTimes()

    failAfter(300 millis) {
      new SetupMenu(mockIO).run()
    }
  }
}
