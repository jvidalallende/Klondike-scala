package klondike.views

import klondike.models.Card

abstract class CardView() {

  // For text console, representation of a card should fit into seven characters, counting brackets

  protected def valueToString(value: Int): String

  protected def suitToString(suit: String): String

  protected def upturnedView(card: Card): String = f"[${valueToString(card.value)} ${suitToString(card.suit)}]"

  protected def downturnedView(card: Card): String = "[XXXXX]"

  protected def emptyCardView : String = "       "

  def draw(card: Card, io: IOManager): Unit = {
    val cardString = if (card.upturned) upturnedView(card) else downturnedView(card)
    io.write(cardString)
  }

  def drawEmpty(io: IOManager): Unit = {
    io.write(emptyCardView)
  }
}
