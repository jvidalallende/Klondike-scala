package klondike.models

class Game(__board: Board) {

  val board: Board = __board

  override def equals(that: Any): Boolean = {
    that match {
      case that: Game => this.board == that.board
      case _ => false
    }
  }
}
