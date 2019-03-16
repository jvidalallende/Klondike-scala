package klondike.views

import klondike.exceptions.ExitGameException
import klondike.models.Game

class ExitCommand(__title: String) extends Command {

  override val title: String = __title

  override def execute(game: Game): Game = {
    throw ExitGameException()
  }
}
