package klondike.views

object Launcher extends App {
  MainMenuBuilder.build(SpanishGameFactory, RealIOManager).run()
}
