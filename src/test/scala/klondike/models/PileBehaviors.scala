package klondike.models

import klondike.exceptions.EmptyPileException
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite

trait PileBehaviors extends MockFactory {
  this: FunSuite =>

  def emptyPileBehaviors(pile: Pile) {

    test(s"givenAnEmpty${pile.name}_whenPickingFromIt_thenRaisesException") {
      intercept[EmptyPileException] {
        pile.pick()
      }
    }

    test(s"givenAnEmpty${pile.name}_whenCheckingIfItIsEmpty_thenItIsEmpty") {
      assert(pile.empty)
    }
  }

  def pileWithOneCardBehaviors(pile: Pile) {

    test(s"givenA${pile.name}WithOneCard_whenCheckingIfItIsEmpty_thenItIsNotEmpty") {
      assert(pile.cards.length == 1)
      assert(!pile.empty)
    }

    test(s"givenA${pile.name}WithOneCard_whenComparedToAListWithTheSameCard_thenTheyAreNotEqual") {
      assert(pile.cards.length == 1)
      assert(pile != List(pile.cards.head))
    }

    test(s"givenA${pile.name}WithOneCard_whenPickingFromIt_thenReturnsTheCardAndAnEmpty${pile.name}") {
      assert(pile.cards.length == 1)
      val (card, newPile) = pile.pick()
      assert(card == pile.cards.head)
      assert(newPile.empty)
    }

  }
}
