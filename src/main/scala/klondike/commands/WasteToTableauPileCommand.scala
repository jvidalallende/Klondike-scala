package klondike.commands

import klondike.controllers.MovementFactory
import klondike.io.IOManager
import klondike.models._
import klondike.utils.ListHelpers

class WasteToTableauPileCommand(__title: String, __movementFactory: MovementFactory, __io: IOManager) extends Command {

  override val title: String = __title
  private val _movementFactory = __movementFactory
  private val _io = __io

  override def execute(board: Board): Board = {
    val (tableauPile, tableauPileIndex) = PileRetriever.tableauPile(board, "destination", _io)
    val (wasteAfterMove, tableauPileAfterMove) = _movementFactory.toTableauPile(board.waste, tableauPile)
    val newTableauPiles = ListHelpers.replaceAt(board.tableauPiles, tableauPileIndex, new TableauPile(tableauPileAfterMove))
    new Board(board.deck, new Waste(wasteAfterMove), board.foundations, newTableauPiles)
  }
}
