package klondike.views

import klondike.commands.{Command, CommandFactory}
import klondike.controllers.MovementFactory

object MainMenuBuilder {

  def build(gameFactory: GameFactory): MainMenu = {
    val io = RealIOManager
    val movementFactory = new MovementFactory(gameFactory.tableauPileValidator)
    val commandFactory = new CommandFactory(movementFactory, io)
    val commandsVector = Vector[Command](
      commandFactory.hitDeck,
      commandFactory.wasteToFoundation,
      commandFactory.wasteToTableauPile,
      commandFactory.tableauPileToFoundation,
      commandFactory.foundationToTableauPile,
      commandFactory.tableauPileToTableauPile,
      commandFactory.exit)
    new MainMenu(commandsVector, gameFactory, io)
  }
}
