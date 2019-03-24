package klondike.models

import klondike.test_utils.TestModels._
import org.scalatest.FunSuite

class WasteTest extends FunSuite with PileBehaviors {

  testsFor(emptyPileBehaviors(emptyWaste))
  testsFor(pileWithOneCardBehaviors(wasteWithCard(aceOfGolds)))

  test("givenAWasteWithTwoCards_whenUsingConstructorFromPile_thenNewWasteIsEqualToOriginalOne") {
    val original = new Waste(aceOfGolds :: twoOfClubsSpanish :: Nil)
    val copy = new Waste(original)
    assert(original == copy)
  }

  test("givenTwoDownturnedCards_whenBuildingAWasteWithIt_thenCardsInTheWasteAreUpturn") {
    val waste = new Waste(aceOfGolds :: twoOfClubsSpanish :: Nil)
    assert(aceOfGolds.upturn() :: twoOfClubsSpanish.upturn() :: Nil == waste.cards)
  }

  test("givenAWaste_whenPuttingADownturnedCardAndPickingIt_thenItReturnsTheCardButUpturned") {
    val deck = emptyWaste.put(aceOfGolds.downturn())
    assert((aceOfGolds.upturn(), new Waste(Nil)) == deck.pick())
  }
}
