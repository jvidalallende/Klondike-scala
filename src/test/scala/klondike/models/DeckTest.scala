package klondike.models

import klondike.test_utils.TestModels._
import org.scalatest.FunSuite

class DeckTest extends FunSuite with PileBehaviors {

  testsFor(emptyPileBehaviors(emptyDeck))
  testsFor(pileWithOneCardBehaviors(deckWithCard(aceOfGolds)))

  test("givenADeckWithTwoCards_whenUsingConstructorFromPile_thenNewDeckIsEqualToOriginalOne") {
    val original = new Deck(aceOfGolds :: twoOfClubsSpanish :: Nil)
    val copy = new Deck(original)
    assert(original == copy)
  }

  test("givenTwoUpturnCards_whenBuildingADeckWithThem_thenCardsInTheDeckAreDownturned") {
    val waste = new Deck(aceOfGolds.upturn() :: twoOfClubsSpanish.upturn() :: Nil)
    assert(aceOfGolds.downturn() :: twoOfClubsSpanish.downturn() :: Nil == waste.cards)
  }

  test("givenADeck_whenPuttingAnUpturnedCardAndPickingIt_thenItReturnsTheCardButDownturned") {
    val deck = emptyDeck.put(aceOfGolds.upturn())
    assert((aceOfGolds.downturn(), new Deck(Nil)) == deck.pick())
  }
}
