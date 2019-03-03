package controllers

import models.Pile

abstract class Movement {

  def move(source: Pile, destination: Pile, numberOfCards: Int): (Pile, Pile)

}
