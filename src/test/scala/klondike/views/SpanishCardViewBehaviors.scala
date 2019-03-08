package klondike.views

import klondike.models.SpanishCard
import org.scalatest.FunSuite
import org.scalamock.scalatest.MockFactory

trait SpanishCardViewBehaviors extends MockFactory {
  this: FunSuite =>

  def checkView(card: SpanishCard, expected: String) {

    test(s"givenSpanishCard${card.toString}_whenViewingIt_thenItShouldBe$expected") {
      val mockIOManager = mock[IOManager]
      (mockIOManager.write(_: String)).expects(expected)
      val view = new SpanishCardView(card, mockIOManager)
      view.draw()
    }
  }

}
