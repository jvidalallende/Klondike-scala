package klondike.commands

import klondike.controllers.MovementBuilder
import klondike.views._

class CommandBuilder(__io: IOManager) {

  private val _movementBuilder = new MovementBuilder(SpanishGameFactory.tableauPileValidator)
  private val _io = __io

  def hitDeck: Command = new HitDeckCommand("Hit Deck", _movementBuilder)

  def wasteToFoundation: Command =
    new WasteToFoundationCommand("Move from waste to one of the foundations", _movementBuilder, _io)

  def wasteToTableauPile: Command =
    new WasteToTableauPileCommand("Move from waste to one of the tableau piles", _movementBuilder, _io)

  def tableauPileToFoundation: Command =
    new TableauPileToFoundationCommand("Move from one of the tableau piles to one of the foundations", _movementBuilder, _io)

  def foundationToTableauPile: Command =
    new FoundationToTableauPileCommand("Move from one of the foundations to one of the tableau piles", _movementBuilder, _io)

  def tableauPileToTableauPile: Command =
    new BetweenTableauPilesCommand("Move from one tableau pile to another", _movementBuilder, _io)

  def exit: Command = new ExitCommand("Exit")
}
