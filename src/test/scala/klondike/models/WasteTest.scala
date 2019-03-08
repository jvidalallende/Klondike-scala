package klondike.models

import org.scalatest.FunSuite

class WasteTest extends FunSuite {

  val aceOfGolds = new SpanishCard(1, "golds")
  val twoOfCups = new SpanishCard(2, "cups")

  test("givenTwoDownturnedCards_whenBuildingAWasteWithIt_thenCardsInTheWasteAreUpturn") {
    val waste = new Waste(aceOfGolds :: twoOfCups :: Nil)
    assert(aceOfGolds.upturn()::twoOfCups.upturn()::Nil == waste.cards)
  }

  test("givenAWasteWithOneCard_whenPickingFromIt_thenReturnsTheCardAndAnEmptyWaste") {
    val aceOfGolds = new SpanishCard(1, "golds")
    val deck = new Waste(aceOfGolds :: Nil)
    assert((aceOfGolds.upturn(), new Waste(Nil)) == deck.pick())
  }

  test("givenAWaste_whenPuttingADownturnedCardAndPickingIt_thenItReturnsTheCardButUpturned") {
    val deck = new Waste(Nil).put(aceOfGolds.downturn())
    assert((aceOfGolds.upturn(), new Waste(Nil)) == deck.pick())
  }
}
