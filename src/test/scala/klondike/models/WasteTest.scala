package klondike.models

import org.scalatest.FunSuite

class WasteTest extends FunSuite with PileBehaviors {

  val aceOfGolds = new SpanishCard(1, "golds")
  val twoOfCups = new SpanishCard(2, "cups")

  testsFor(emptyPileBehaviors(new Waste(Nil)))
  testsFor(pileWithOneCardBehaviors(new Waste(aceOfGolds :: Nil)))

  test("givenAWasteWithTwoCards_whenUsingConstructorFromPile_thenNewWasteIsEqualToOriginalOne") {
    val original = new Waste(aceOfGolds :: twoOfCups :: Nil)
    val copy = new Waste(original)
    assert(original == copy)
  }

  test("givenTwoDownturnedCards_whenBuildingAWasteWithIt_thenCardsInTheWasteAreUpturn") {
    val waste = new Waste(aceOfGolds :: twoOfCups :: Nil)
    assert(aceOfGolds.upturn() :: twoOfCups.upturn() :: Nil == waste.cards)
  }

  test("givenAWaste_whenPuttingADownturnedCardAndPickingIt_thenItReturnsTheCardButUpturned") {
    val deck = new Waste(Nil).put(aceOfGolds.downturn())
    assert((aceOfGolds.upturn(), new Waste(Nil)) == deck.pick())
  }
}
