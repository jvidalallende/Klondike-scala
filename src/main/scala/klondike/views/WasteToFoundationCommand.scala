package klondike.views

import klondike.controllers.MovementFactory
import klondike.exceptions.InvalidValueException
import klondike.models._
import klondike.util.ListHelpers

class WasteToFoundationCommand(__title: String, __io: IOManager) extends Command {

  override val title: String = __title
  private val _io = __io

  override def execute(game: Game): Game = {
    val numberOfFoundations = game.board.foundations.length
    val foundationIndex = _io.readInt(s"What foundation is the destination? [1-$numberOfFoundations]") - 1
    val foundation = game.board.foundation(foundationIndex)

    val (wasteAfterMove, foundationAfterMove) = MovementFactory.moveToFoundation[Waste]()(game.board.waste, foundation)
    val newFoundations = ListHelpers.replaceAt(game.board.foundations, foundationIndex, new Foundation(foundationAfterMove))
    val board = new Board(game.board.deck, new Waste(wasteAfterMove), newFoundations, game.board.tableauPiles)
    new Game(board)
  }
}
