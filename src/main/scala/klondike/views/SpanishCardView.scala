package klondike.views

import klondike.models.Card

object SpanishCardView extends CardView {

  override protected def valueToString(value: Int): String = {
    value match {
      case _ if value <= 7 => s" $value"
      case _ => s"${value + 2}"
    }
  }

  override protected def suitToString(suit: String): String = {
    suit match {
      case "golds" => "GOLDS "
      case "cups" => "CUPS  "
      case "swords" => "SWORDS"
      case "clubs" => "CLUBS "
    }
  }

  override protected def downturnedView(card: Card): String = "[XXXXXXXXX]"

  override protected def placeholderView: String = "[         ]"

  override protected def whitespace: String = "           "
}
