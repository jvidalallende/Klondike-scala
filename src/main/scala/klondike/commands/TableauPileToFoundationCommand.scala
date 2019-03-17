package klondike.commands

import klondike.controllers.MovementFactory
import klondike.models._
import klondike.utils.ListHelpers
import klondike.views.IOManager

class TableauPileToFoundationCommand(__title: String, __io: IOManager) extends Command {

  override val title: String = __title
  private val _io = __io

  override def execute(game: Game): Game = {
    val tableauPileIndex = _io.readInt(s"What tableau pile is the source? [1-${game.board.tableauPiles.length}]") - 1
    val tableauPile = game.board.tableauPile(tableauPileIndex)
    val foundationIndex = _io.readInt(s"What foundation is the destination? [1-${game.board.foundations.length}]") - 1
    val foundation = game.board.foundation(foundationIndex)

    val (tableauPileAfterMove, foundationAfterMove) = MovementFactory.moveToFoundation[TableauPile]()(tableauPile, foundation)
    val newFoundations = ListHelpers.replaceAt(game.board.foundations, foundationIndex, new Foundation(foundationAfterMove))
    val newTableauPiles = ListHelpers.replaceAt(game.board.tableauPiles, tableauPileIndex, new TableauPile(tableauPileAfterMove))
    val board = new Board(game.board.deck, game.board.waste, newFoundations, newTableauPiles)
    new Game(board)
  }
}
