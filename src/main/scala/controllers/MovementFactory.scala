package controllers

import exceptions.InvalidMoveException
import models.Pile

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
}
