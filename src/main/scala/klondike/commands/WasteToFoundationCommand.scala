package klondike.commands

import klondike.controllers.MovementFactory
import klondike.io.IOManager
import klondike.models._
import klondike.utils.ListHelpers

class WasteToFoundationCommand(__title: String, __movementFactory: MovementFactory, __io: IOManager) extends Command {

  override val title: String = __title
  private val _movementFactory = __movementFactory
  private val _io = __io

  override def execute(board: Board): Board = {
    val (foundation, foundationIndex) = PileRetriever.foundation(board, "destination", _io)
    val (wasteAfterMove, foundationAfterMove) = _movementFactory.toFoundation(board.waste, foundation)
    val newFoundations = ListHelpers.replaceAt(board.foundations, foundationIndex, new Foundation(foundationAfterMove))
    new Board(board.deck, new Waste(wasteAfterMove), newFoundations, board.tableauPiles)
  }
}
