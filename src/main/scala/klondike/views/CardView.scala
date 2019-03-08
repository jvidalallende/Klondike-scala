package klondike.views

import klondike.models.Card

abstract class CardView(__card: Card, __io: IOManager) {

  protected val _card: Card = __card
  protected val _io: IOManager = __io

  protected def valueToString: String

  protected def suitToString: String

  protected def upturnedView: String = f"[${this.valueToString} ${this.suitToString}]"

  protected def downturnedView: String = "[XXXXX]"

  def draw(): Unit = {
    val cardString = if (_card.upturned) upturnedView else downturnedView
    _io.write(cardString)
  }
}
