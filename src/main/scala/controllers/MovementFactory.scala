package controllers

import exceptions.InvalidMoveException
import models.Pile

/* All moves returned by this factory are functions with the same parameter types:
   f(Pile, Pile, Int) --> (Pile, Pile) */
class MovementFactory {

  def deckToWaste(): (Pile, Pile, Int) => (Pile, Pile) = {
    def move(deck: Pile, waste: Pile, numberOfCards: Int = 1): (Pile, Pile) = {
      numberOfCards match {
        case 0 => (deck, waste)
        case _ =>
          val pickResult = deck.pick()
          move(pickResult._2, waste.put(pickResult._1), numberOfCards - 1)
      }
    }

    move
  }

  def wasteToFoundation(): (Pile, Pile, Int) => (Pile, Pile) = {
    def validMove(waste: Pile, tableauPile: Pile): Boolean = {
      val topOfWaste = waste.pick()._1
      tableauPile match {
        case f if f.empty() => topOfWaste.isMin()
        case _ =>
          val topOfFoundation = tableauPile.pick()._1
          (topOfWaste.suit() == topOfFoundation.suit()) && (topOfWaste.value() - topOfFoundation.value() == 1)
      }
    }

    def move(waste: Pile, tableauPile: Pile, numberOfCards: Int = 1): (Pile, Pile) = {
      numberOfCards match {
        case 0 => (waste, tableauPile)
        case _ =>
          if (!validMove(waste, tableauPile)) {
            throw InvalidMoveException()
          }
          val pickResult = waste.pick()
          move(pickResult._2, tableauPile.put(pickResult._1), numberOfCards - 1)
      }
    }

    move
  }

  def wasteToTableauPile(): (Pile, Pile, Int) => (Pile, Pile) = {
    def validMove(waste: Pile, tableauPile: Pile): Boolean = {
      val topOfWaste = waste.pick()._1
      tableauPile match {
        case f if f.empty() => topOfWaste.isMax()
        case _ =>
          val topOfTableauPile = tableauPile.pick()._1
          //TODO: consider different stacking rules
          (topOfWaste.suit() != topOfTableauPile.suit()) && (topOfTableauPile.value() - topOfWaste.value() == 1)
      }
    }

    def move(waste: Pile, tableauPile: Pile, numberOfCards: Int = 1): (Pile, Pile) = {
      numberOfCards match {
        case 0 => (waste, tableauPile)
        case _ =>
          if (!validMove(waste, tableauPile)) {
            throw InvalidMoveException()
          }
          val pickResult = waste.pick()
          move(pickResult._2, tableauPile.put(pickResult._1), numberOfCards - 1)
      }
    }

    move
  }
}
