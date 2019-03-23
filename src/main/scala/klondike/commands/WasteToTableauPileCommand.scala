package klondike.commands

import klondike.controllers.MovementBuilder
import klondike.models._
import klondike.utils.ListHelpers
import klondike.views.IOManager

class WasteToTableauPileCommand(__title: String, __movementBuilder: MovementBuilder, __io: IOManager) extends Command {

  override val title: String = __title
  private val _movementBuilder = __movementBuilder
  private val _io = __io

  override def execute(game: Game): Game = {
    val tableauPileIndex = _io.readInt(s"What tableau pile is the destination? [1-${game.board.tableauPiles.length}]") - 1
    val tableauPile = game.board.tableauPile(tableauPileIndex)

    val (wasteAfterMove, tableauPileAfterMove) = _movementBuilder.toTableauPile(game.board.waste, tableauPile)
    val newTableauPiles = ListHelpers.replaceAt(game.board.tableauPiles, tableauPileIndex, new TableauPile(tableauPileAfterMove))
    val board = new Board(game.board.deck, new Waste(wasteAfterMove), game.board.foundations, newTableauPiles)
    new Game(board)
  }
}
