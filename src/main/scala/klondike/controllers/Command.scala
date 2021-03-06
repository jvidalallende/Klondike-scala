package klondike.controllers

import klondike.models.Board

abstract class Command {

  def title: String

  def execute(board: Board): Board
}
