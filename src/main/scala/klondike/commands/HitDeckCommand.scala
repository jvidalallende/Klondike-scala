package klondike.commands

import klondike.controllers.MovementFactory
import klondike.models.{Board, Deck, Game, Waste}

class HitDeckCommand(__title: String) extends Command {

  override val title: String = __title

  override def execute(game: Game): Game = {
    val (deckAfterMove, wasteAfterMove) = MovementFactory.deckToWaste()(game.board.deck, game.board.waste)
    val board = new Board(new Deck(deckAfterMove), new Waste(wasteAfterMove), game.board.foundations, game.board.tableauPiles)
    new Game(board)
  }
}
