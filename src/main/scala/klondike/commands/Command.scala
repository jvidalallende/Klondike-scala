package klondike.commands

import klondike.models.Game

abstract class Command {

  def title: String

  def execute(game: Game): Game
}
