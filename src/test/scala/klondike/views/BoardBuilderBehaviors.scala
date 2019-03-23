package klondike.views

import klondike.models.{Board, TableauPile}
import org.scalatest.FunSuite

trait BoardBuilderBehaviors {
  this: FunSuite =>

  def checkBuild(name: String, buildFunction: Int => Board) {

    test(s"givenA${name}_whenABoardIsBuilt_thenItMeetsBoardRequirements") {
      val board = buildFunction(7)
      assert(!board.deck.cards.head.upturned)
      assert(board.waste.empty)
      assert(board.foundations.forall(f => f.empty))
      assert(board.tableauPiles.forall(tp => tp.cards.head.upturned))
      assert(board.tableauPiles.forall(tp => tp.cards.tail.forall(c => !c.upturned)))
      assert(checkLengthOfConsecutiveTableauPiles(board.tableauPiles))
    }
  }

  private def checkLengthOfConsecutiveTableauPiles(piles: List[TableauPile]): Boolean = {
    piles.length match {
      case n if n <= 1 => true
      case _ => (piles.tail.head.cards.length - piles.head.cards.length == 1) && checkLengthOfConsecutiveTableauPiles(piles.tail)
    }
  }
}
