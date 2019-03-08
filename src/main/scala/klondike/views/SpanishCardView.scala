package klondike.views

import klondike.models.{Card, SpanishCard}

class SpanishCardView(__card: SpanishCard, __io: IOManager) extends CardView(__card, __io) {

  override protected def valueToString: String = {
    _card.value match {
      case _ if _card.value <= 7 => s" ${_card.value}"
      case _ =>
        val viewValue = _card.value + 2
        s"$viewValue"
    }
  }

  override protected def suitToString: String = {
    _card.suit match {
      case "golds" => "GO"
      case "cups" => "CU"
      case "swords" => "SW"
      case "clubs" => "CL"
    }
  }
}
