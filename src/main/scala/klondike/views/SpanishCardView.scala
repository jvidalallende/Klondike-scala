package klondike.views

object SpanishCardView extends CardView {

  override protected def valueToString(value: Int): String = {
    value match {
      case _ if value <= 7 => s" $value"
      case _ => s"${value + 2}"
    }
  }

  override protected def suitToString(suit: String): String = {
    suit match {
      case "golds" => "GO"
      case "cups" => "CU"
      case "swords" => "SW"
      case "clubs" => "CL"
    }
  }
}
