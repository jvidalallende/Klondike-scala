package klondike.views

import klondike.models.{Card, FrenchCard}

import scala.util.Random

object FrenchBoardBuilder extends BoardBuilder {

  override protected def createCards(): List[Card] = {
    val cardSequence = for (value <- FrenchCard.MIN_VALUE to FrenchCard.MAX_VALUE; suit <- FrenchCard.suits) yield new FrenchCard(value, suit)
    Random.shuffle(cardSequence).toList
  }
}
