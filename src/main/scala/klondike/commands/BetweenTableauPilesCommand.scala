package klondike.commands

import klondike.controllers.MovementFactory
import klondike.exceptions.InvalidMoveException
import klondike.models._
import klondike.utils.ListHelpers
import klondike.views.IOManager

class BetweenTableauPilesCommand(__title: String, __movementFactory: MovementFactory, __io: IOManager) extends Command {

  override val title: String = __title
  private val _movementFactory = __movementFactory
  private val _io = __io

  override def execute(game: Game): Game = {
    val (source, sourceIndex) = PileRetriever.tableauPile(game, "source", _io)
    val (destination, destinationIndex) = PileRetriever.tableauPile(game, "destination", _io)
    if (sourceIndex == destinationIndex) {
      throw InvalidMoveException("Cannot select the same pile as source an destination")
    }

    val cardsToMove = _io.readInt(s"How many cards should be moved? ")
    val (sourceAfterMove, destinationAfterMove) = _movementFactory.betweenTableauPiles(cardsToMove)(source, destination)
    val newTableauPiles = ListHelpers.replaceAt(
      ListHelpers.replaceAt(game.board.tableauPiles, sourceIndex, new TableauPile(sourceAfterMove)),
      destinationIndex,
      new TableauPile(destinationAfterMove))

    new Game(new Board(game.board.deck, game.board.waste, game.board.foundations, newTableauPiles))
  }
}
