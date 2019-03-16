package klondike.controllers

import klondike.exceptions.InvalidMoveException
import klondike.models._

// All moves returned by this factory are functions with the same parameter types: f(Pile, Pile) --> (Pile, Pile)
object MovementFactory {

  private def moveOne[A, B](validate: (Card, Pile[B]) => Boolean)(source: Pile[A], destination: Pile[B]): (Pile[A], Pile[B]) = {
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

  private def foundationValidator(card: Card, foundation: Pile[Foundation]): Boolean = {
    foundation match {
      case f if f.empty => card.isMin
      case _ =>
        val topOfFoundation = foundation.pick()._1
        (card.suit == topOfFoundation.suit) && (card.value - topOfFoundation.value == 1)
    }
  }

  private def tableauPileValidator(card: Card, tableauPile: Pile[TableauPile]): Boolean = {
    tableauPile match {
      case f if f.empty => card.isMax
      case _ =>
        val topOfTableauPile = tableauPile.pick()._1
        //TODO: consider supporting different stacking rules
        (card.suit != topOfTableauPile.suit) && (topOfTableauPile.value - card.value == 1)
    }
  }


  def deckToWaste(): (Pile[Deck], Pile[Waste]) => (Pile[Deck], Pile[Waste]) = {
    // No need to validate this movement, as the Waste has no requirements on which cards it accepts
    moveOne[Deck, Waste]((_: Card, _: Pile[Waste]) => true)
  }

  def moveToFoundation[A](): (Pile[A], Pile[Foundation]) => (Pile[A], Pile[Foundation]) = {
    moveOne[A, Foundation](foundationValidator)
  }

  def moveToTableauPile[A](): (Pile[A], Pile[TableauPile]) => (Pile[A], Pile[TableauPile]) = {
    moveOne(tableauPileValidator)
  }

  def tableauPileToTableauPile(numberOfCards: Int): (TableauPile, TableauPile) => (TableauPile, TableauPile) = {
    moveMany(tableauPileValidator, numberOfCards)
  }
}
