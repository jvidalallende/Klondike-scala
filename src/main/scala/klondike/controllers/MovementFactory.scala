package klondike.controllers

import klondike.exceptions.InvalidMoveException
import klondike.models.{Card, Pile, TableauPile}

// All moves returned by this factory are functions with the same parameter types: f(Pile, Pile) --> (Pile, Pile)
object MovementFactory {

  private def moveOne(validate: (Card, Pile) => Boolean)(source: Pile, destination: Pile): (Pile, Pile) = {
    val (pickedCard, newSource) = source.pick()
    if (!validate(pickedCard, destination)) {
      throw InvalidMoveException()
    }
    (newSource, destination.put(pickedCard))
  }

  private def moveMany(validate: (Card, Pile) => Boolean, numberOfCards: Int)(source: TableauPile, destination: TableauPile)
  : (Pile, Pile) = {
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

  private def tableauPileValidator(card: Card, tableauPile: Pile): Boolean = {
    tableauPile match {
      case f if f.empty => card.isMax
      case _ =>
        val topOfTableauPile = tableauPile.pick()._1
        //TODO: consider supporting different stacking rules
        (card.suit != topOfTableauPile.suit) && (topOfTableauPile.value - card.value == 1)
    }
  }


  def deckToWaste(): (Pile, Pile) => (Pile, Pile) = {
    // No need to validate this movement, as the Waste has no requirements on which cards it accepts
    moveOne((_: Card, _: Pile) => true)
  }

  def wasteToFoundation(): (Pile, Pile) => (Pile, Pile) = {
    moveOne(foundationValidator)
  }

  def tableauPileToFoundation(): (Pile, Pile) => (Pile, Pile) = {
    moveOne(foundationValidator)
  }

  def wasteToTableauPile(): (Pile, Pile) => (Pile, Pile) = {
    moveOne(tableauPileValidator)
  }

  def foundationToTableauPile(): (Pile, Pile) => (Pile, Pile) = {
    moveOne(tableauPileValidator)
  }

  def tableauPileToTableauPile(numberOfCards: Int): (TableauPile, TableauPile) => (Pile, Pile) = {
    moveMany(tableauPileValidator, numberOfCards)
  }
}
