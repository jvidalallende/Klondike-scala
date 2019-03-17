package klondike.commands

import klondike.views._

object Commands {

  def hitDeck: Command = new HitDeckCommand("Hit Deck")

  def wasteToFoundation(io: IOManager): Command = new WasteToFoundationCommand("Move from waste to one of the foundations", io)

  def wasteToTableauPile(io: IOManager): Command = new WasteToTableauPileCommand("Move from waste to one of the tableau piles", io)

  def tableauPileToFoundation(io: IOManager): Command = new TableauPileToFoundationCommand("Move from one of the tableau piles to one of the foundations", io)

  def exit: Command = new ExitCommand("Exit")
}
