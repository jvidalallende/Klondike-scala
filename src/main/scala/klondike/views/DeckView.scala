package klondike.views

import klondike.models.Pile


class DeckView(__cardView: CardView) extends PileView(__cardView) {

  private def prefix(pile: Pile): String = {
    pile.cards.length match {
      case n if n > 3 => "|||"
      case 3 => " ||"
      case 2 => "  |"
      case _ => "   "
    }
  }

  override def draw(pile: Pile, io: IOManager): Unit = {
    io.write(prefix(pile))
    drawTopOfPile(pile, io)
  }
}
