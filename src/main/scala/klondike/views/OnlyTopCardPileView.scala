package klondike.views

import klondike.models.Pile

object OnlyTopCardPileView {

  def draw(pile: Pile, cardView: CardView, io: IOManager): Unit = {
    if (pile.empty) {
      io.write("[     ]")
    }
    else {
      cardView.draw(pile.pick()._1, io)
    }
  }
}

