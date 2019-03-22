package klondike.views

import klondike.commands.{Command, Commands}
import klondike.models._

object Launcher extends App {
  val gameFactory = new SpanishGameFactory(7)
  val io = RealIOManager
  val commandsVector = Vector[Command](
    Commands.hitDeck,
    Commands.wasteToFoundation(io),
    Commands.wasteToTableauPile(io),
    Commands.tableauPileToFoundation(io),
    Commands.foundationToTableauPile(io),
    Commands.tableauPileToTableauPile(io),
    Commands.exit)
  val menu = new MainMenu(commandsVector, gameFactory, io)
  menu.run()
}
