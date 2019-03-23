package klondike.commands

import klondike.controllers.MovementFactory
import klondike.models._
import klondike.utils.ListHelpers
import klondike.views.IOManager

class TableauPileToFoundationCommand(__title: String, __movementFactory: MovementFactory, __io: IOManager) extends Command {

  override val title: String = __title
  private val _movementFactory = __movementFactory
  private val _io = __io

  override def execute(game: Game): Game = {
    val (tableauPile, tableauPileIndex) = PileRetriever.tableauPile(game, "source", _io)
    val (foundation, foundationIndex) = PileRetriever.foundation(game, "destination", _io)
    val (tableauPileAfterMove, foundationAfterMove) = _movementFactory.toFoundation(tableauPile, foundation)
    val newFoundations = ListHelpers.replaceAt(game.board.foundations, foundationIndex, new Foundation(foundationAfterMove))
    val newTableauPiles = ListHelpers.replaceAt(game.board.tableauPiles, tableauPileIndex, new TableauPile(tableauPileAfterMove))
    new Game(new Board(game.board.deck, game.board.waste, newFoundations, newTableauPiles))
  }
}
