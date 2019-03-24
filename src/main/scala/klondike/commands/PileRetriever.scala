package klondike.commands

import klondike.io.IOManager
import klondike.models.{Board, Foundation, TableauPile}

object PileRetriever {

  def foundation(board: Board, target: String, io: IOManager) : (Foundation, Int) = {
    val index = io.readInt(s"What foundation is the $target? [1-${board.foundations.length}]") - 1
    (board.foundation(index), index)
  }

  def tableauPile(board: Board, target: String, io: IOManager): (TableauPile, Int) = {
    val index = io.readInt(s"What tableau pile is the $target? [1-${board.tableauPiles.length}]") - 1
    (board.tableauPile(index), index)
  }
}
