package klondike.views

import klondike.models.Card

object FrenchCardView extends CardView {

  override protected def valueToString(value: Int): String = {
    value match {
      case 1 => " A"
      case _ if 2 <= value && value <= 9 => s" $value"
      case 10 => "10"
      case 11 => " J"
      case 12 => " Q"
      case 13 => " K"
    }
  }

  override protected def suitToString(suit: String): String = {
    suit match {
      case "clubs" => "CLUBS   "
      case "diamonds" => "DIAMONDS"
      case "hearts" => "HEARTS  "
      case "spades" => "SPADES  "
    }
  }

  override protected def downturnedView(card: Card): String = "[XXXXXXXXXXX]"

  override protected def placeholderView: String = "[           ]"

  override protected def whitespace: String = "             "
}
