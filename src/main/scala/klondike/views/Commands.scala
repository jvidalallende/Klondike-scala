package klondike.views

object Commands {

  def hitDeck: Command = new HitDeckCommand("Hit Deck")

  def wasteToFoundation(io: IOManager): Command = new WasteToFoundationCommand("Move from waste to one of the foundations", io)

  def exit: Command = new ExitCommand("Exit")
}
