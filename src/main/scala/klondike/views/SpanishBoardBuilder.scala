package klondike.views

import klondike.models.{Card, SpanishCard}

import scala.util.Random

object SpanishBoardBuilder extends BoardBuilder {

  override protected def createCards(): List[Card] = {
    val cardSequence = for (value <- SpanishCard.MIN_VALUE to SpanishCard.MAX_VALUE; suit <- SpanishCard.suits) yield new SpanishCard(value, suit)
    Random.shuffle(cardSequence).toList
  }
}
