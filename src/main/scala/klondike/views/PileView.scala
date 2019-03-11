package klondike.views

import klondike.models.Pile

abstract class PileView(__cardView: CardView) {

  protected val _cardView: CardView = __cardView

  protected def drawTopOfPile(pile: Pile, io: IOManager) : Unit = {
    if (pile.empty) {
      io.write("[     ]")
    }
    else {
      _cardView.draw(pile.pick()._1, io)
    }
  }

  def draw(pile: Pile, io: IOManager) : Unit
}
