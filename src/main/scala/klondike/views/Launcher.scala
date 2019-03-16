package klondike.views

import klondike.models.{Board, Deck, Game, Waste}

object Launcher extends App {
  val board = new Board(new Deck(Nil), new Waste(Nil), Nil, Nil)
  val game = new Game(board)
  val io = RealIOManager
  val commandsVector = Vector[Command](Commands.hitDeck, Commands.exit)
  val menu = new MainMenu(commandsVector, game, io)
  menu.run()
}
