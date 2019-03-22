package klondike.views

import klondike.commands.Command
import klondike.exceptions.ExitGameException
import klondike.models.{FoundationsFullGoal, Game}

class MainMenu(__commands: Vector[Command], __gameFactory: GameFactory, __io: IOManager) {

  private val _commands = __commands
  private val _gameFactory = __gameFactory
  private val _io = __io

  def run(): Unit = {
    var game = new Game(_gameFactory.initialBoard)
    var keepRunning = true
    val boardView = new BoardView(_gameFactory.cardView, _io)
    while (keepRunning) {
      boardView.draw(game.board)
      val command = askUserForCommand()
      try {
        game = command.execute(game)
        if (FoundationsFullGoal.done(game.board)) {
          keepRunning = false;
          _io.write("\n *** Well done! ***\n\n")
        }
        else {
          _io.write("\n *** Successful move! ***\n\n")
        }
      }
      catch {
        case exit: ExitGameException =>
          keepRunning = false
          _io.write(s"${exit.getMessage}\n")
        case e: Exception => _io.write(s"\n *** Error running command: ${e.getMessage} ***\n\n")
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
