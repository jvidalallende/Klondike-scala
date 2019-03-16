package klondike.models

import klondike.exceptions.EmptyPileException
import org.scalatest.FunSuite

class DeckTest extends FunSuite {

  val aceOfGolds = new SpanishCard(1, "golds")
  val twoOfCups = new SpanishCard(2, "cups")

  test("givenAEmptyDeck_whenPickingFromIt_thenRaisesException") {
    intercept[EmptyPileException] {
      new Deck(Nil).pick()
    }
  }

  test("givenAEmptyDeck_whenCheckingIfItIsEmpty_thenItIsEmpty") {
    assert(new Deck(Nil).empty)
  }

  test("givenADeckWithOneCard_whenCheckingIfItIsEmpty_thenItIsNotEmpty") {
    assert(!new Deck(aceOfGolds :: Nil).empty)
  }

  test("givenADeck_whenComparedToAListOfCards_thenTheyAreNotEqual") {
    val cards = List(aceOfGolds.upturn(), twoOfCups)
    assert(new Deck(cards) != cards)
  }

  test("givenADeckWithTwoCards_whenUsingConstructorFromPile_thenNewDeckIsEqualToOriginalOne") {
    val original = new Deck(aceOfGolds :: twoOfCups :: Nil)
    val copy = new Deck(original)
    assert(original == copy)
  }

  test("givenTwoUpturnCards_whenBuildingADeckWithThem_thenCardsInTheDeckAreDownturned") {
    val waste = new Deck(aceOfGolds.upturn() :: twoOfCups.upturn() :: Nil)
    assert(aceOfGolds.downturn() :: twoOfCups.downturn() :: Nil == waste.cards)
  }

  test("givenADeckWithOneCard_whenPickingFromIt_thenReturnsTheCardAndAnEmptyDeck") {
    val aceOfGolds = new SpanishCard(1, "golds")
    val deck = new Deck(aceOfGolds :: Nil)
    assert((aceOfGolds, new Deck(Nil)) == deck.pick())
  }

  test("givenADeck_whenPuttingAnUpturnedCardAndPickingIt_thenItReturnsTheCardButDownturned") {
    val deck = new Deck(Nil).put(aceOfGolds.upturn())
    assert((aceOfGolds.downturn(), new Deck(Nil)) == deck.pick())
  }
}
