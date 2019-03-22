package klondike.models

abstract class Goal {

  def done(board: Board): Boolean
}
