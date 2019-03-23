package klondike.test_utils

import klondike.models._
import klondike.utils.ListHelpers

object TestModels {

  val aceOfGolds: SpanishCard = new SpanishCard(1, "golds", true)
  val kingOfGolds: SpanishCard = new SpanishCard(SpanishCard.MAX_VALUE, "golds", true)
  val knightOfCups: SpanishCard = new SpanishCard(SpanishCard.MAX_VALUE - 1, "cups", true)

  val emptyDeck: Deck = new Deck(Nil)
  val emptyWaste: Waste = new Waste(Nil)
  val emptyFoundation: Foundation = new Foundation(Nil)
  val emptyTableauPile: TableauPile = new TableauPile(Nil)

  val emptyFoundations: List[Foundation] = List(Nil, Nil, Nil, Nil).map(x => new Foundation(x))
  val emptyTableauPiles: List[TableauPile] = List(Nil, Nil, Nil, Nil, Nil, Nil, Nil).map(x => new TableauPile(x))
  val emptyBoard: Board = new Board(new Deck(Nil), new Waste(Nil), emptyFoundations, emptyTableauPiles)

  def deck(card: Card): Deck = new Deck(card :: Nil)

  def waste(card: Card): Waste = new Waste(card :: Nil)

  def foundation(card: Card): Foundation = new Foundation(card :: Nil)

  def tableauPile(card: Card): TableauPile = new TableauPile(card :: Nil)

  def emptyBoardWithDeck(deck: Deck): Board = new Board(deck, emptyWaste, emptyFoundations, emptyTableauPiles)

  def emptyBoardWithWaste(waste: Waste): Board = new Board(emptyDeck, waste, emptyFoundations, emptyTableauPiles)

  def emptyBoardWithFoundation(foundation: Foundation, index: Int): Board = {
    val foundations = ListHelpers.replaceAt(emptyFoundations, index, foundation)
    new Board(emptyDeck, emptyWaste, foundations, emptyTableauPiles)
  }

  def emptyBoardWithTableauPile(tableauPile: TableauPile, index: Int): Board = {
    val tableauPiles = ListHelpers.replaceAt(emptyTableauPiles, index, tableauPile)
    new Board(emptyDeck, emptyWaste, emptyFoundations, tableauPiles)
  }
}
