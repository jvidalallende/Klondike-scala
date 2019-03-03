package controllers

import models.Pile

/* All moves returned by this factory are functions with the same parameter types:
   f(Pile, Pile, Int) --> (Pile, Pile) */
class MovementFactory {

  def deckToWasteMove(): (Pile, Pile, Int) => (Pile, Pile) = {
    def move(source: Pile, destination: Pile, numberOfCards: Int = 1): (Pile, Pile) = {
      numberOfCards match {
        case 0 => (source, destination)
        case _ =>
          val pickResult = source.pick()
          move(pickResult._2, destination.put(pickResult._1), numberOfCards - 1)
      }
    }

    move
  }
}
