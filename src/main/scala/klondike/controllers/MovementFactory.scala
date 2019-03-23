package klondike.controllers

import klondike.exceptions.InvalidMoveException
import klondike.models.{Card, Pile, TableauPile}

class MovementFactory(__tableauPileValidator: (Card, Pile) => Boolean) {

  private val _tableauPileValidator = __tableauPileValidator

  def deckToWaste: (Pile, Pile) => (Pile, Pile) = {
    // No need to validate this movement, as the Waste has no requirements on which cards it accepts
    moveOne((_: Card, _: Pile) => true)
  }

  def toFoundation: (Pile, Pile) => (Pile, Pile) = {
    moveOne(foundationValidator)
  }

  def toTableauPile: (Pile, Pile) => (Pile, Pile) = {
    moveOne(_tableauPileValidator)
  }

  def betweenTableauPiles(numberOfCards: Int): (TableauPile, TableauPile) => (TableauPile, TableauPile) = {
    moveMany(_tableauPileValidator, numberOfCards)
  }

  private def moveOne(validate: (Card, Pile) => Boolean)(source: Pile, destination: Pile): (Pile, Pile) = {
    val (pickedCard, newSource) = source.pick()
    if (!validate(pickedCard, destination)) {
      throw InvalidMoveException()
    }
    (newSource, destination.put(pickedCard))
  }

  private def moveMany(validate: (Card, TableauPile) => Boolean, numberOfCards: Int)(source: TableauPile, destination: TableauPile)
  : (TableauPile, TableauPile) = {
    numberOfCards match {
      case n if n < 0 => throw InvalidMoveException("The number of cards to move must be positive")
      case 0 => (source, destination)
      case _ =>
        val (pickedCards, newSource) = source.pick(numberOfCards)
        if (!validate(pickedCards.head, destination)) {
          throw InvalidMoveException()
        }
        (newSource, destination.put(pickedCards))
    }
  }

  private def foundationValidator(card: Card, foundation: Pile): Boolean = {
    foundation match {
      case f if f.empty => card.isMin
      case _ =>
        val topOfFoundation = foundation.pick()._1
        (card.suit == topOfFoundation.suit) && (card.value - topOfFoundation.value == 1)
    }
  }
}
