package klondike.models

import klondike.exceptions.EmptyPileException
import org.scalatest.FunSuite

class WasteTest extends FunSuite {

  val aceOfGolds = new SpanishCard(1, "golds")
  val twoOfCups = new SpanishCard(2, "cups")

  test("givenOneEmptyWaste_whenPickingFromIt_thenRaisesException") {
    intercept[EmptyPileException] {
      new Waste(Nil).pick()
    }
  }

  test("givenOneEmptyWaste_whenCheckingIfItIsEmpty_thenItIsEmpty") {
    assert(new Waste(Nil).empty)
  }

  test("givenOneWasteWithOneCard_whenCheckingIfItIsEmpty_thenItIsNotEmpty") {
    assert(!new Waste(aceOfGolds :: Nil).empty)
  }

  test("givenAWaste_whenComparedToAListOfCards_thenTheyAreNotEqual") {
    val cards = List(aceOfGolds.upturn(), twoOfCups)
    assert(new Waste(cards) != cards)
  }

  test("givenTwoDownturnedCards_whenBuildingAWasteWithIt_thenCardsInTheWasteAreUpturn") {
    val waste = new Waste(aceOfGolds :: twoOfCups :: Nil)
    assert(aceOfGolds.upturn() :: twoOfCups.upturn() :: Nil == waste.cards)
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
