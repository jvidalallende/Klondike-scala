package klondike.models

import klondike.exceptions.EmptyPileException
import org.scalatest.FunSuite

class DeckTest extends FunSuite {

  val aceOfGolds = new SpanishCard(1, "golds")
  val twoOfCups = new SpanishCard(2, "cups")

  test("givenOneEmptyDeck_whenPickingFromIt_thenRaisesException") {
    intercept[EmptyPileException] {
      new Deck(Nil).pick()
    }
  }

  test("givenOneEmptyDeck_whenCheckingIfItIsEmpty_thenItIsEmpty") {
    assert(new Deck(Nil).empty)
  }

  test("givenOneDeckWithOneCard_whenCheckingIfItIsEmpty_thenItIsNotEmpty") {
    assert(!new Deck(aceOfGolds :: Nil).empty)
  }

  test("givenADeck_whenComparedToAListOfCards_thenTheyAreNotEqual") {
    val cards = List(aceOfGolds.upturn(), twoOfCups)
    assert(new Deck(cards) != cards)
  }

  test("givenTwoUpturnCards_whenBuildingADeckWithThem_thenCardsInTheDeckAreDownturned") {
    val waste = new Deck(aceOfGolds.upturn() :: twoOfCups.upturn() :: Nil)
    assert(aceOfGolds.downturn() :: twoOfCups.downturn() :: Nil == waste.cards)
  }

  test("givenOneDeckWithOneCard_whenPickingFromIt_thenReturnsTheCardAndAnEmptyDeck") {
    val aceOfGolds = new SpanishCard(1, "golds")
    val deck = new Deck(aceOfGolds :: Nil)
    assert((aceOfGolds, new Deck(Nil)) == deck.pick())
  }

  test("givenADeck_whenPuttingAnUpturnedCardAndPickingIt_thenItReturnsTheCardButDownturned") {
    val deck = new Deck(Nil).put(aceOfGolds.upturn())
    assert((aceOfGolds.downturn(), new Deck(Nil)) == deck.pick())
  }
}