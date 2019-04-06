package klondike.controllers

import klondike.exceptions.ExitGameException
import klondike.models.Board

class ExitCommand(__title: String) extends Command {

  override val title: String = __title

  override def execute(board: Board): Board = throw ExitGameException()
}
