package klondike.views

import klondike.models.Card
import org.scalatest.FunSuite
import org.scalamock.scalatest.MockFactory

trait CardViewBehaviors extends MockFactory {
  this: FunSuite =>

  def draw(view: CardView, card: Card, expected: String) {

    test(s"givenCard${card.toString}_whenViewingIt_thenItShouldBe$expected") {
      val mockIOManager = mock[IOManager]
      (mockIOManager.write(_: String)).expects(expected)
      view.draw(card, mockIOManager)
    }

    // There should be a test that verifies that empty space has the same size of upturned/downturned views
    // However, scalamock does not support argument capture (will do in 4.2.0 apparently)
  }

  def drawEmpty(view: CardView, expected: String) {

    test(s"givenSpanishCardView_whenViewingItsEmptySpace_thenItShouldBe[$expected]") {
      val mockIOManager = mock[IOManager]
      (mockIOManager.write(_: String)).expects(expected)
      SpanishCardView.drawEmpty(mockIOManager)
    }
  }

}
