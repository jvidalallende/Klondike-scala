package klondike.views

import klondike.io.IOManager
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

  def drawWhitespace(cardType: String, view: CardView, expected: String) {

    test(s"given${cardType}CardView_whenViewingItsWhitespace_thenItShouldBe'$expected'") {
      val mockIO = mock[IOManager]
      (mockIO.write(_: String)).expects(expected)
      view.drawWhitespace(mockIO)
    }
  }

  def drawPlaceholder(cardType: String, view: CardView, expected: String) {

    test(s"given${cardType}CardView_whenViewingItsPlaceholder_thenItShouldBe'$expected'") {
      val mockIO = mock[IOManager]
      (mockIO.write(_: String)).expects(expected)
      view.drawPlaceholder(mockIO)
    }
  }

}
