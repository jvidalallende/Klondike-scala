package klondike.commands

import klondike.controllers.MovementFactory
import klondike.models._
import klondike.utils.ListHelpers
import klondike.views.IOManager

class FoundationToTableauPileCommand(__title: String, __movementFactory: MovementFactory, __io: IOManager) extends Command {

  override val title: String = __title
  private val _movementFactory = __movementFactory
  private val _io = __io

  override def execute(game: Game): Game = {
    val (foundation, foundationIndex) = PileRetriever.foundation(game, "source", _io)
    val (tableauPile, tableauPileIndex) = PileRetriever.tableauPile(game, "destination", _io)

    val (foundationAfterMove, tableauPileAfterMove) = _movementFactory.toTableauPile(foundation, tableauPile)
    val newTableauPiles = ListHelpers.replaceAt(game.board.tableauPiles, tableauPileIndex, new TableauPile(tableauPileAfterMove))
    val newFoundations = ListHelpers.replaceAt(game.board.foundations, foundationIndex, new Foundation(foundationAfterMove))

    new Game(new Board(game.board.deck, game.board.waste, newFoundations, newTableauPiles))
  }
}
