package klondike.commands

import klondike.controllers.MovementFactory
import klondike.models._
import klondike.utils.ListHelpers
import klondike.views.IOManager

class WasteToTableauPileCommand(__title: String, __movementFactory: MovementFactory, __io: IOManager) extends Command {

  override val title: String = __title
  private val _movementFactory = __movementFactory
  private val _io = __io

  override def execute(game: Game): Game = {
    val (tableauPile, tableauPileIndex) = PileRetriever.tableauPile(game, "destination", _io)
    val (wasteAfterMove, tableauPileAfterMove) = _movementFactory.toTableauPile(game.board.waste, tableauPile)
    val newTableauPiles = ListHelpers.replaceAt(game.board.tableauPiles, tableauPileIndex, new TableauPile(tableauPileAfterMove))
    new Game(new Board(game.board.deck, new Waste(wasteAfterMove), game.board.foundations, newTableauPiles))
  }
}
