package klondike.views

import klondike.models.Card
import klondike.test_utils.IOMocks
import org.scalatest.FunSuite
import org.scalamock.scalatest.MockFactory

trait CardViewBehaviors extends MockFactory {
  this: FunSuite =>

  def draw(view: CardView, card: Card, expected: String) {

    test(s"givenCard${card.toString}_whenViewingIt_thenItShouldBe'$expected'") {
      val mockIO = mock[IOManager]
      (mockIO.write(_: String)).expects(expected)
      view.draw(card, mockIO)
    }

    test(s"givenCard${card.toString}_whenViewingIt_thenItsViewHasTheSameSizeAsEmptyView") {
      val writeAccumulator = IOMocks.writeAccumulator
      view.draw(card, writeAccumulator)
      view.drawWhitespace(writeAccumulator)
      val values = writeAccumulator.asList
      assert(values.length == 2)
      assert(values.head.length == values.tail.head.length)
    }
  }

  def drawEmpty(view: CardView, expected: String) {

    test(s"givenSpanishCardView_whenViewingItsEmptySpace_thenItShouldBe'$expected'") {
      val mockIO = mock[IOManager]
      (mockIO.write(_: String)).expects(expected)
      SpanishCardView.drawWhitespace(mockIO)
    }
  }

}
