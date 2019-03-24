package klondike.test_utils

import klondike.models._
import klondike.utils.ListHelpers

object TestModels {

  // Spanish Cards
  val aceOfGolds: SpanishCard = new SpanishCard(1, "golds", true)
  val twoOfGolds: SpanishCard = new SpanishCard(2, "golds", true)
  val kingOfGolds: SpanishCard = new SpanishCard(SpanishCard.MAX_VALUE, "golds", true)

  val twoOfCups: SpanishCard = new SpanishCard(2, "cups", true)
  val threeOfCups: SpanishCard = new SpanishCard(3, "cups", true)
  val fourOfCups: SpanishCard = new SpanishCard(4, "cups", true)
  val knightOfCups: SpanishCard = new SpanishCard(SpanishCard.MAX_VALUE - 1, "cups", true)
  val kingOfCups: SpanishCard = new SpanishCard(SpanishCard.MAX_VALUE, "cups", true)

  val threeOfSwords = new SpanishCard(3, "swords", true)
  val kingOfSwords: SpanishCard = new SpanishCard(SpanishCard.MAX_VALUE, "swords", true)

  val twoOfClubsSpanish: SpanishCard = new SpanishCard(2, "clubs", true)
  val kingOfClubsSpanish: SpanishCard = new SpanishCard(SpanishCard.MAX_VALUE, "clubs", true)

  // French Cards
  val aceOfHearts: FrenchCard = new FrenchCard(1, "hearts", true)
  val twoOfHearts: FrenchCard = new FrenchCard(2, "hearts", true)
  val kingOfHearts: FrenchCard = new FrenchCard(FrenchCard.MAX_VALUE, "hearts", true)

  val twoOfDiamonds: FrenchCard = new FrenchCard(2, "diamonds", true)
  val threeOfDiamonds: FrenchCard = new FrenchCard(3, "diamonds", true)
  val fourOfDiamonds: FrenchCard = new FrenchCard(4, "diamonds", true)
  val knightOfDiamonds: FrenchCard = new FrenchCard(FrenchCard.MAX_VALUE - 1, "diamonds", true)
  val kingOfDiamonds: FrenchCard = new FrenchCard(FrenchCard.MAX_VALUE, "diamonds", true)

  val threeOfSpades = new FrenchCard(3, "spades", true)
  val kingOfSpades: FrenchCard = new FrenchCard(FrenchCard.MAX_VALUE, "spades", true)

  val twoOfClubsFrench: FrenchCard = new FrenchCard(2, "clubs", true)
  val kingOfClubsFrench: FrenchCard = new FrenchCard(FrenchCard.MAX_VALUE, "clubs", true)


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
