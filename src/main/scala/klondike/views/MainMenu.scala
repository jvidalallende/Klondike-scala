package klondike.views

import klondike.commands.Command
import klondike.exceptions.ExitGameException
import klondike.models.Game

class MainMenu(__commands: Vector[Command], __initialGame: Game, __io: IOManager) {

  private val _initialGame = __initialGame
  private val _commands = __commands
  private val _io = __io

  def run(): Unit = {
    var game = _initialGame
    var keepRunning = true
    while (keepRunning) {
      BoardView.draw(game.board, SpanishCardView, _io)
      val command = askUserForCommand()
      try {
        game = command.execute(game)
      }
      catch {
        case exit: ExitGameException =>
          _io.write(s"${exit.getMessage}\n")
          keepRunning = false
        case e: Exception => _io.write(s"Error running command: ${e.getMessage}\n")
      }
    }
  }

  private def askUserForCommand(): Command = {
    var choice = 0
    do {
      printCommands()
      choice = _io.readInt("Your choice? ")
      _io.write("\n")
    }
    while (!validChoice(choice))
    _commands(choice - 1)
  }

  private def printCommands(): Unit = {
    // Create a list of tuples (command, index), starting at index 1
    val commandsWithIndex = _commands.zip(Stream from 1)
    _io.write("Select an option:\n")
    for ((command, index) <- commandsWithIndex) {
      _io.write(s"[$index] ${command.title}\n")
    }
  }

  private def validChoice(index: Int): Boolean = {
    if (0 < index && index <= _commands.length) {
      true
    }
    else {
      _io.write(s"Invalid choice ($index). Please select a value in the range [1-${_commands.length}]\n")
      false
    }
  }
}
