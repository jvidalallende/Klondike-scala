package klondike.views

import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite
import org.scalatest.time.SpanSugar._
import org.scalatest.concurrent.Timeouts._

class MainMenuTest extends FunSuite with MockFactory {

  test("givenAMenuBuilderWithSpanishGameFactory_whenCreatingTheMenuAndSelectingTheExitOption_thenTheTestsEndsWithoutTimeout") {
    val mockIO = mock[IOManager]
    (mockIO.write(_: String)).expects(*).anyNumberOfTimes()
    (mockIO.readInt _).expects(*).returning(7).noMoreThanOnce()
    val menu = MainMenuBuilder.build(SpanishGameFactory, mockIO)
    failAfter(200 millis) {
      menu.run()
    }
    succeed
  }
}
