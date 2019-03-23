package klondike.commands

import klondike.controllers.MovementBuilder
import klondike.models._
import klondike.utils.ListHelpers
import klondike.views.IOManager

class WasteToFoundationCommand(__title: String, __movementBuilder: MovementBuilder, __io: IOManager) extends Command {

  override val title: String = __title
  private val _movementBuilder = __movementBuilder
  private val _io = __io

  override def execute(game: Game): Game = {
    val foundationIndex = _io.readInt(s"What foundation is the destination? [1-${game.board.foundations.length}]") - 1
    val foundation = game.board.foundation(foundationIndex)

    val (wasteAfterMove, foundationAfterMove) = _movementBuilder.toFoundation(game.board.waste, foundation)
    val newFoundations = ListHelpers.replaceAt(game.board.foundations, foundationIndex, new Foundation(foundationAfterMove))
    val board = new Board(game.board.deck, new Waste(wasteAfterMove), newFoundations, game.board.tableauPiles)
    new Game(board)
  }
}
