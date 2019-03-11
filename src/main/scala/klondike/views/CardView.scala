package klondike.views

import klondike.models.Card

abstract class CardView() {

  protected def valueToString(value: Int): String

  protected def suitToString(suit: String): String

  protected def upturnedView(card: Card): String = f"[${valueToString(card.value)} ${suitToString(card.suit)}]"

  protected def downturnedView(card: Card): String = "[XXXXX]"

  def draw(card: Card, io: IOManager): Unit = {
    val cardString = if (card.upturned) upturnedView(card) else downturnedView(card)
    io.write(cardString)
  }
}
