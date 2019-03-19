package klondike.views

import klondike.models.Pile
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite

trait OnlyTopCardPileViewBehaviors extends MockFactory {
  this: FunSuite =>

  def checkView[A](pile: Pile, cardView: CardView, cardString: String, message: String) {

    test(s"given${pile.name}${message}_whenViewingIt_thenItShouldBeDrawnAsExpected") {
      val mockIOManager = mock[IOManager]
      (mockIOManager.write(_: String)).expects(cardString)
      OnlyTopCardPileView.draw(pile, cardView, mockIOManager)
    }
  }

}
