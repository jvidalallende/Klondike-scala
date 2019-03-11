package klondike.views

import klondike.models.Pile

class OnlyTopPileView(__cardView: CardView) {

  protected val _cardView: CardView = __cardView

  def draw(pile: Pile, io: IOManager): Unit = {
    if (pile.empty) {
      io.write("[     ]")
    }
    else {
      _cardView.draw(pile.pick()._1, io)
    }
  }
}

