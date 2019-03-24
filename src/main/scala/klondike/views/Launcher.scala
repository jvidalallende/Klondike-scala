package klondike.views

import klondike.io.RealIOManager

object Launcher extends App {
  new SetupMenu(RealIOManager).run()
}
