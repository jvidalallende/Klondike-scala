package klondike.views

import klondike.commands.{Command, CommandBuilder}
import klondike.models._

object Launcher extends App {
  val gameFactory = SpanishGameFactory
  val io = RealIOManager
  val commandBuilder = new CommandBuilder(io)
  val commandsVector = Vector[Command](
    commandBuilder.hitDeck,
    commandBuilder.wasteToFoundation,
    commandBuilder.wasteToTableauPile,
    commandBuilder.tableauPileToFoundation,
    commandBuilder.foundationToTableauPile,
    commandBuilder.tableauPileToTableauPile,
    commandBuilder.exit)
  val menu = new MainMenu(commandsVector, gameFactory, io)
  menu.run()
}
