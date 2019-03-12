package klondike.models

import klondike.exceptions.InvalidValueException

class Board(__deck: Deck, __waste: Waste, __foundations: List[Foundation], __tableauPiles: List[TableauPile]) {

  private val _deck: Deck = __deck
  private val _waste: Waste = __waste
  private val _foundations = __foundations
  private val _tableauPiles = __tableauPiles

  def deck: Deck = _deck

  def waste: Waste = _waste

  def foundations: List[Foundation] = _foundations

  def tableauPiles: List[TableauPile] = _tableauPiles

  def foundation(index: Int): Foundation = {
    pileFromList(index, _foundations)
  }

  def tableauPile(index: Int): TableauPile = {
    pileFromList(index, _tableauPiles)
  }

  private def pileFromList[A](index: Int, piles: List[A]): A = {
    index match {
      case _ if index <= 0 || piles.isEmpty => throw InvalidValueException(s"Pile not available")
      case 1 => piles.head
      case _ => pileFromList(index - 1, piles.tail)
    }
  }
}
