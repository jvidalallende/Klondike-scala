package klondike.views

import klondike.exceptions.ExitGameException

class SetupMenu(__io: IOManager) {
  private val _io = __io

  def run(): Unit = {
    _io.write("***** Welcome to Klondike! *****\n\n")
    try {
      do {
        _io.write("[1] Play game with Spanish cards\n")
        _io.write("[2] Play game with French cards\n")
        _io.write("[Any other input] Exit\n\n")
        _io.readInt("What do you want to do? ") match {
          case 1 => MainMenuBuilder.build(SpanishGameFactory, _io).run()
          case 2 => MainMenuBuilder.build(FrenchGameFactory, _io).run()
          case _ => throw new NumberFormatException()
        }

      } while(true)
    }
    catch {
      case _: NumberFormatException => _io.write("\nGood Bye!\n")
    }
  }
}
