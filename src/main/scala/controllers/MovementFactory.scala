package controllers

import exceptions.InvalidMoveException
import models.{Card, Pile, TableauPile}

/* All moves returned by this factory are functions with the same parameter types:
   f(Pile, Pile, Int) --> (Pile, Pile) */
class MovementFactory {

  private def move(validator: (Pile, Pile) => Boolean)(source: Pile, destination: Pile, numberOfCards: Int = 1)
  : (Pile, Pile) = {
    numberOfCards match {
      case 0 => (source, destination)
      case _ =>
        if (!validator(source, destination)) {
          throw InvalidMoveException()
        }
        val pickResult = source.pick()
        move(validator)(pickResult._2, destination.put(pickResult._1), numberOfCards - 1)
    }
  }

  private def moveBetweenTableauPiles(validator: (Card, TableauPile) => Boolean)
                                     (source: TableauPile, destination: TableauPile, numberOfCards: Int = 1)
  : (Pile, Pile) = {
    numberOfCards match {
      case 0 => (source, destination)
      case _ =>
        val (pickedCards, newSource) = source.pick(numberOfCards)
        if (!validator(pickedCards.head, destination)) {
          throw InvalidMoveException()
        }
        (newSource, destination.put(pickedCards))
    }
  }

  def deckToWaste(): (Pile, Pile, Int) => (Pile, Pile) = {
    def validator(deck: Pile, waste: Pile): Boolean = true

    move(validator)
  }

  def wasteToFoundation(): (Pile, Pile, Int) => (Pile, Pile) = {
    def validator(waste: Pile, foundation: Pile): Boolean = {
      val topOfWaste = waste.pick()._1
      foundation match {
        case f if f.empty() => topOfWaste.isMin()
        case _ =>
          val topOfFoundation = foundation.pick()._1
          (topOfWaste.suit() == topOfFoundation.suit()) && (topOfWaste.value() - topOfFoundation.value() == 1)
      }
    }

    move(validator)
  }

  def tableauPileToFoundation(): (Pile, Pile, Int) => (Pile, Pile) = {
    def validator(tableauPile: Pile, foundation: Pile): Boolean = {
      val topOfTableauPile = tableauPile.pick()._1
      foundation match {
        case f if f.empty() => topOfTableauPile.isMin()
        case _ =>
          val topOfFoundation = foundation.pick()._1
          (topOfTableauPile.suit() == topOfFoundation.suit()) && (topOfTableauPile.value() - topOfFoundation.value() == 1)
      }
    }

    move(validator)
  }

  def wasteToTableauPile(): (Pile, Pile, Int) => (Pile, Pile) = {
    def validator(waste: Pile, tableauPile: Pile): Boolean = {
      val topOfWaste = waste.pick()._1
      tableauPile match {
        case f if f.empty() => topOfWaste.isMax()
        case _ =>
          val topOfTableauPile = tableauPile.pick()._1
          //TODO: consider supporting different stacking rules
          (topOfWaste.suit() != topOfTableauPile.suit()) && (topOfTableauPile.value() - topOfWaste.value() == 1)
      }
    }

    move(validator)
  }

  def foundationToTableauPile(): (Pile, Pile, Int) => (Pile, Pile) = {
    def validator(foundation: Pile, tableauPile: Pile): Boolean = {
      val topOfFoundation = foundation.pick()._1
      tableauPile match {
        case f if f.empty() => topOfFoundation.isMax()
        case _ =>
          val topOfTableauPile = tableauPile.pick()._1
          //TODO: consider supporting different stacking rules
          (topOfFoundation.suit() != topOfTableauPile.suit()) && (topOfTableauPile.value() - topOfFoundation.value() == 1)
      }
    }

    move(validator)
  }

  def tableauPileToTableauPile(): (TableauPile, TableauPile, Int) => (Pile, Pile) = {
    def validator(topOfPick: Card, destination: TableauPile): Boolean = {
      destination match {
        case _ if destination.empty() => topOfPick.isMax()
        case _ =>
          val topOfDestination = destination.pick()._1
          //TODO: consider supporting different stacking rules
          (topOfPick.suit() != topOfDestination.suit()) && (topOfDestination.value() - topOfPick.value() == 1)
      }

    }

    moveBetweenTableauPiles(validator)
  }
}
