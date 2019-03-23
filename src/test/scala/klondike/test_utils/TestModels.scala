package klondike.test_utils

import klondike.models._
import klondike.utils.ListHelpers

object TestModels {

  // golds
  val aceOfGolds: SpanishCard = new SpanishCard(1, "golds", true)
  val twoOfGolds: SpanishCard = new SpanishCard(2, "golds", true)
  val kingOfGolds: SpanishCard = new SpanishCard(SpanishCard.MAX_VALUE, "golds", true)

  // cups
  val twoOfCups: SpanishCard = new SpanishCard(2, "cups", true)
  val threeOfCups: SpanishCard = new SpanishCard(3, "cups", true)
  val fourOfCups: SpanishCard = new SpanishCard(4, "cups", true)
  val knightOfCups: SpanishCard = new SpanishCard(SpanishCard.MAX_VALUE - 1, "cups", true)
  val kingOfCups: SpanishCard = new SpanishCard(SpanishCard.MAX_VALUE, "cups", true)

  // swords
  val threeOfSwords = new SpanishCard(3, "swords", true)
  val kingOfSwords: SpanishCard = new SpanishCard(SpanishCard.MAX_VALUE, "swords", true)

  // clubs
  val twoOfClubs: SpanishCard = new SpanishCard(2, "clubs", true)
  val kingOfClubs: SpanishCard = new SpanishCard(SpanishCard.MAX_VALUE, "clubs", true)

  val emptyDeck: Deck = new Deck(Nil, "EmptyDeck")
  val emptyWaste: Waste = new Waste(Nil, "EmptyWaste")
  val emptyFoundation: Foundation = new Foundation(Nil, "EmptyFoundation")
  val emptyTableauPile: TableauPile = new TableauPile(Nil, "EmptyTableauPile")

  val emptyFoundations: List[Foundation] = List(Nil, Nil, Nil, Nil).map(x => new Foundation(x))
  val emptyTableauPiles: List[TableauPile] = List(Nil, Nil, Nil, Nil, Nil, Nil, Nil).map(x => new TableauPile(x))
  val emptyBoard: Board = new Board(new Deck(Nil), new Waste(Nil), emptyFoundations, emptyTableauPiles)

  def deckWithCard(card: Card): Deck = new Deck(card :: Nil)

  def wasteWithCard(card: Card): Waste = new Waste(card :: Nil)

  def foundationWithCard(card: Card): Foundation = new Foundation(card :: Nil)

  def tableauPileWithCard(card: Card): TableauPile = new TableauPile(card :: Nil)

  def emptyBoardWithDeck(deck: Deck): Board = new Board(deck, emptyWaste, emptyFoundations, emptyTableauPiles)

  def emptyBoardWithWaste(waste: Waste): Board = new Board(emptyDeck, waste, emptyFoundations, emptyTableauPiles)

  def emptyBoardWithFoundations(foundations: List[Foundation]): Board = new Board(emptyDeck, emptyWaste, foundations, emptyTableauPiles)

  def emptyBoardWithFoundation(foundation: Foundation, index: Int): Board = {
    val foundations = ListHelpers.replaceAt(emptyFoundations, index, foundation)
    new Board(emptyDeck, emptyWaste, foundations, emptyTableauPiles)
  }

  def emptyBoardWithTableauPile(tableauPile: TableauPile, index: Int): Board = {
    val tableauPiles = ListHelpers.replaceAt(emptyTableauPiles, index, tableauPile)
    new Board(emptyDeck, emptyWaste, emptyFoundations, tableauPiles)
  }
}
