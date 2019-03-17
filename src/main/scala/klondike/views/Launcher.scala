package klondike.views

import klondike.commands.{Command, Commands}
import klondike.models._

object Launcher extends App {
  val cards = new SpanishCard(1, "golds") :: new SpanishCard(2, "golds") :: new SpanishCard(1, "cups") :: Nil
  val foundations = new Foundation(Nil) :: new Foundation(Nil) :: new Foundation(Nil) :: new Foundation(Nil) :: Nil
  val board = new Board(new Deck(cards), new Waste(Nil), foundations, Nil)
  val game = new Game(board)
  val io = RealIOManager
  val commandsVector = Vector[Command](Commands.hitDeck, Commands.wasteToFoundation(io), Commands.exit)
  val menu = new MainMenu(commandsVector, game, io)
  menu.run()
}
