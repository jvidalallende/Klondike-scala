package klondike.controllers

import klondike.exceptions.InvalidMoveException
import klondike.io.IOManager
import klondike.models._
import klondike.utils.ListHelpers

class BetweenTableauPilesCommand(__title: String, __movementFactory: MovementFactory, __io: IOManager) extends Command {

  override val title: String = __title
  private val _movementFactory = __movementFactory
  private val _io = __io

  override def execute(board: Board): Board = {
    val (source, sourceIndex) = PileRetriever.tableauPile(board, "source", _io)
    val (destination, destinationIndex) = PileRetriever.tableauPile(board, "destination", _io)
    if (sourceIndex == destinationIndex) {
      throw InvalidMoveException("Cannot select the same pile as source and destination")
    }

    val cardsToMove = _io.readInt(s"How many cards should be moved? ")
    val (sourceAfterMove, destinationAfterMove) = _movementFactory.betweenTableauPiles(cardsToMove)(source, destination)
    val newTableauPiles = ListHelpers.replaceAt(
      ListHelpers.replaceAt(board.tableauPiles, sourceIndex, new TableauPile(sourceAfterMove)),
      destinationIndex,
      new TableauPile(destinationAfterMove))

    new Board(board.deck, board.waste, board.foundations, newTableauPiles)
  }
}
