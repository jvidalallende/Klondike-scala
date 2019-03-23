package klondike.views

import klondike.models.{Board, Card, Pile}

abstract class GameFactory {

  // Builds the initial game board, with all the cards in it distributed among the Piles
  def initialBoard(numberOfTableauPiles: Int): Board

  // Responsible of drawing the cards
  def cardView: CardView

  // Returns a function that indicates if a Card is allowed to be placed in a TableauPile
  def tableauPileValidator: (Card, Pile) => Boolean
}
