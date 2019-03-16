package klondike.views

object Commands {

  def exit: Command = new ExitCommand("Exit")
  def hitDeck: Command = new HitDeckCommand("Hit Deck")
}
