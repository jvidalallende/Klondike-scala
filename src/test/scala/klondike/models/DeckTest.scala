package klondike.models

import org.scalatest.FunSuite

class DeckTest extends FunSuite with PileBehaviors {

  val aceOfGolds = new SpanishCard(1, "golds")
  val twoOfCups = new SpanishCard(2, "cups")

  testsFor(emptyPileBehaviors(new Deck(Nil)))
  testsFor(pileWithOneCardBehaviors(new Deck(aceOfGolds :: Nil)))

  test("givenADeckWithTwoCards_whenUsingConstructorFromPile_thenNewDeckIsEqualToOriginalOne") {
    val original = new Deck(aceOfGolds :: twoOfCups :: Nil)
    val copy = new Deck(original)
    assert(original == copy)
  }

  test("givenTwoUpturnCards_whenBuildingADeckWithThem_thenCardsInTheDeckAreDownturned") {
    val waste = new Deck(aceOfGolds.upturn() :: twoOfCups.upturn() :: Nil)
    assert(aceOfGolds.downturn() :: twoOfCups.downturn() :: Nil == waste.cards)
  }

  test("givenADeck_whenPuttingAnUpturnedCardAndPickingIt_thenItReturnsTheCardButDownturned") {
    val deck = new Deck(Nil).put(aceOfGolds.upturn())
    assert((aceOfGolds.downturn(), new Deck(Nil)) == deck.pick())
  }
}
