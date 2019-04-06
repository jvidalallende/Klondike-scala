package klondike.models

class Game(__board: Board, __goal: Goal) {

  val board: Board = __board
  val goal: Goal = __goal

  override def equals(that: Any): Boolean = {
    that match {
      case that: Game => this.board == that.board && this.goal == that.goal
      case _ => false
    }
  }
}

