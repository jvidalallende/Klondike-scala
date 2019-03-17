package klondike.views

import klondike.models.Pile

object OnlyTopCardPileView {

  def draw[A](pile: Pile[A], cardView: CardView, io: IOManager): Unit = {
    if (pile.empty) {
      io.write("[     ]")
    }
    else {
      cardView.draw(pile.pick()._1, io)
    }
  }
}

