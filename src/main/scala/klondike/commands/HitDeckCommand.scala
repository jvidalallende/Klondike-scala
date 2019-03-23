package klondike.commands

import klondike.controllers.MovementFactory
import klondike.models.{Board, Deck, Game, Waste}

class HitDeckCommand(__title: String, __movementFactory: MovementFactory) extends Command {

  override val title: String = __title
  private val _movementFactory = __movementFactory

  override def execute(game: Game): Game = {
    val (deckAfterMove, wasteAfterMove) = _movementFactory.deckToWaste(game.board.deck, game.board.waste)
    val board = new Board(new Deck(deckAfterMove), new Waste(wasteAfterMove), game.board.foundations, game.board.tableauPiles)
    new Game(board)
  }
}
