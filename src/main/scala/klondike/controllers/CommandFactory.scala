package klondike.controllers

import klondike.io.IOManager

class CommandFactory(__movementFactory: MovementFactory, __io: IOManager) {

  private val _movementFactory = __movementFactory
  private val _io = __io

  def hitDeck: Command = new HitDeckCommand("Hit Deck", _movementFactory)

  def wasteToFoundation: Command =
    new WasteToFoundationCommand("Move from waste to one of the foundations", _movementFactory, _io)

  def wasteToTableauPile: Command =
    new WasteToTableauPileCommand("Move from waste to one of the tableau piles", _movementFactory, _io)

  def tableauPileToFoundation: Command =
    new TableauPileToFoundationCommand("Move from one of the tableau piles to one of the foundations", _movementFactory, _io)

  def foundationToTableauPile: Command =
    new FoundationToTableauPileCommand("Move from one of the foundations to one of the tableau piles", _movementFactory, _io)

  def tableauPileToTableauPile: Command =
    new BetweenTableauPilesCommand("Move from one tableau pile to another", _movementFactory, _io)

  def exit: Command = new ExitCommand("Exit")
}
