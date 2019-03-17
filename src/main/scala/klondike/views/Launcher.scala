package klondike.views

import klondike.commands.{Command, Commands}
import klondike.models._

object Launcher extends App {
  val game = new Game(SpanishBoardBuilder.build())
  val io = RealIOManager
  val commandsVector = Vector[Command](
    Commands.hitDeck,
    Commands.wasteToFoundation(io),
    Commands.wasteToTableauPile(io),
    Commands.tableauPileToFoundation(io),
    Commands.foundationToTableauPile(io),
    Commands.tableauPileToTableauPile(io),
    Commands.exit)
  val menu = new MainMenu(commandsVector, game, io)
  menu.run()
}
