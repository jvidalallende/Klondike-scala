package klondike.controllers

import klondike.io.IOManager
import klondike.models._
import klondike.utils.ListHelpers

class TableauPileToFoundationCommand(__title: String, __movementFactory: MovementFactory, __io: IOManager) extends Command {

  override val title: String = __title
  private val _movementFactory = __movementFactory
  private val _io = __io

  override def execute(board: Board): Board = {
    val (tableauPile, tableauPileIndex) = PileRetriever.tableauPile(board, "source", _io)
    val (foundation, foundationIndex) = PileRetriever.foundation(board, "destination", _io)
    val (tableauPileAfterMove, foundationAfterMove) = _movementFactory.toFoundation(tableauPile, foundation)
    val newFoundations = ListHelpers.replaceAt(board.foundations, foundationIndex, new Foundation(foundationAfterMove))
    val newTableauPiles = ListHelpers.replaceAt(board.tableauPiles, tableauPileIndex, new TableauPile(tableauPileAfterMove))
    new Board(board.deck, board.waste, newFoundations, newTableauPiles)
  }
}
