package klondike.views

import klondike.models.Pile
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite

trait PileViewBehaviors extends MockFactory {
  this: FunSuite =>

  def checkView[A](pile: Pile[A], pileView: OnlyTopCardPileView, cardString: String, message: String) {

    test(s"given${pile.name}${message}_whenViewingIt_thenItShouldBeDrawnAsExpected") {
      val mockIOManager = mock[IOManager]
      (mockIOManager.write(_: String)).expects(cardString)
      pileView.draw(pile, mockIOManager)
    }
  }

}
