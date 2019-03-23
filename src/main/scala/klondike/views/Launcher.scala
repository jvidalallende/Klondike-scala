package klondike.views

object Launcher extends App {
  val menu = MainMenuBuilder.build(SpanishGameFactory, RealIOManager)
  menu.run()
}
