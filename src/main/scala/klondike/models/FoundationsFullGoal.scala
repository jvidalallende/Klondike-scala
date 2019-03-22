package klondike.models

object FoundationsFullGoal extends Goal {

  override def done(board: Board): Boolean = {
    board.foundations.forall(x => x.full)
  }
}
