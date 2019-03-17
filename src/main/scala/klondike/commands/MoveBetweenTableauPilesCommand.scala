package klondike.commands

import klondike.controllers.MovementFactory
import klondike.exceptions.InvalidMoveException
import klondike.models._
import klondike.utils.ListHelpers
import klondike.views.IOManager

class MoveBetweenTableauPilesCommand(__title: String, __io: IOManager) extends Command {

  override val title: String = __title
  private val _io = __io

  override def execute(game: Game): Game = {
    val sourceIndex = _io.readInt(s"What tableau pile is the source? [1-${game.board.tableauPiles.length}]") - 1
    val destinationIndex = _io.readInt(s"What foundation is the destination? [1-${game.board.foundations.length}]") - 1
    if (sourceIndex == destinationIndex) {
      throw InvalidMoveException("Cannot select the same pile as source an destination")
    }

    val cardsToMove = _io.readInt(s"How many cards should be moved? ")

    val source = game.board.tableauPile(sourceIndex)
    val destination = game.board.tableauPile(destinationIndex)

    val (sourceAfterMove, destinationAfterMove) = MovementFactory.tableauPileToTableauPile(cardsToMove)(source, destination)
    val newTableauPiles = ListHelpers.replaceAt(
      ListHelpers.replaceAt(game.board.tableauPiles, sourceIndex, new TableauPile(sourceAfterMove)),
      destinationIndex,
      new TableauPile(destinationAfterMove))
    val board = new Board(game.board.deck, game.board.waste, game.board.foundations, newTableauPiles)
    new Game(board)
  }
}
