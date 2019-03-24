package klondike.commands

import klondike.controllers.MovementFactory
import klondike.models.{Board, Deck, Waste}

class HitDeckCommand(__title: String, __movementFactory: MovementFactory) extends Command {

  override val title: String = __title
  private val _movementFactory = __movementFactory

  override def execute(board: Board): Board = {
    if (board.deck.empty) {
      new Board(new Deck(board.waste.cards.reverse), new Waste(Nil), board.foundations, board.tableauPiles)
    }
    else {
      val (deckAfterMove, wasteAfterMove) = _movementFactory.deckToWaste(board.deck, board.waste)
      new Board(new Deck(deckAfterMove), new Waste(wasteAfterMove), board.foundations, board.tableauPiles)
    }
  }
}
