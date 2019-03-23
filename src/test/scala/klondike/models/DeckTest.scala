package klondike.models

import klondike.test_utils.TestModels._
import org.scalatest.FunSuite

class DeckTest extends FunSuite with PileBehaviors {

  testsFor(emptyPileBehaviors(emptyDeck))
  testsFor(pileWithOneCardBehaviors(deckWithCard(aceOfGolds)))

  test("givenADeckWithTwoCards_whenUsingConstructorFromPile_thenNewDeckIsEqualToOriginalOne") {
    val original = new Deck(aceOfGolds :: twoOfClubs :: Nil)
    val copy = new Deck(original)
    assert(original == copy)
  }

  test("givenTwoUpturnCards_whenBuildingADeckWithThem_thenCardsInTheDeckAreDownturned") {
    val waste = new Deck(aceOfGolds.upturn() :: twoOfClubs.upturn() :: Nil)
    assert(aceOfGolds.downturn() :: twoOfClubs.downturn() :: Nil == waste.cards)
  }

  test("givenADeck_whenPuttingAnUpturnedCardAndPickingIt_thenItReturnsTheCardButDownturned") {
    val deck = emptyDeck.put(aceOfGolds.upturn())
    assert((aceOfGolds.downturn(), new Deck(Nil)) == deck.pick())
  }
}
