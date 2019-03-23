package klondike.commands

import klondike.models.{Foundation, Game, TableauPile}
import klondike.views.IOManager

object PileRetriever {

  def foundation(game: Game, target: String, io: IOManager) : (Foundation, Int) = {
    val index = io.readInt(s"What foundation is the $target? [1-${game.board.foundations.length}]") - 1
    (game.board.foundation(index), index)
  }

  def tableauPile(game: Game, target: String, io: IOManager): (TableauPile, Int) = {
    val index = io.readInt(s"What tableau pile is the $target? [1-${game.board.tableauPiles.length}]") - 1
    (game.board.tableauPile(index), index)
  }
}
