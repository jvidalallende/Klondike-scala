package klondike.commands

import klondike.controllers.MovementBuilder
import klondike.models.{Board, Deck, Game, Waste}

class HitDeckCommand(__title: String, __movementBuilder: MovementBuilder) extends Command {

  override val title: String = __title
  private val _movementBuilder = __movementBuilder

  override def execute(game: Game): Game = {
    val (deckAfterMove, wasteAfterMove) = _movementBuilder.deckToWaste(game.board.deck, game.board.waste)
    val board = new Board(new Deck(deckAfterMove), new Waste(wasteAfterMove), game.board.foundations, game.board.tableauPiles)
    new Game(board)
  }
}
