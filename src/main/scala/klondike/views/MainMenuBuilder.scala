package klondike.views

import klondike.controllers.{Command, CommandFactory, MovementFactory}
import klondike.io.IOManager

object MainMenuBuilder {

  def build(gameFactory: GameFactory, io: IOManager): MainMenu = {
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
