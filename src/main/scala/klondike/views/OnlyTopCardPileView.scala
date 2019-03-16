package klondike.views

import klondike.models.Pile

class OnlyTopCardPileView(__cardView: CardView) {

  protected val _cardView: CardView = __cardView

  def draw[A](pile: Pile[A], io: IOManager): Unit = {
    if (pile.empty) {
      io.write("[     ]")
    }
    else {
      _cardView.draw(pile.pick()._1, io)
    }
  }
}

