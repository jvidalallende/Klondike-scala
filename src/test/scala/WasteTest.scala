import exceptions.EmptyPileException
import models.{Waste, SpanishCard}
import org.scalatest.FunSuite

class WasteTest extends FunSuite {

  val aceOfGolds = new SpanishCard(1, "golds")

  test("givenOneWasteWithOneCard_whenPickingFromIt_thenReturnsTheCardAndAnEmptyWaste") {
    val aceOfGolds = new SpanishCard(1, "golds")
    val deck = new Waste(aceOfGolds :: Nil)
    assert((aceOfGolds, new Waste(Nil)) == deck.pick())
  }

  test("givenAWaste_whenPuttingADownturnedCardAndPickingIt_thenItReturnsTheCardButUpturned") {
    val deck = new Waste(Nil).put(aceOfGolds.downturn())
    assert((aceOfGolds.upturn(), new Waste(Nil)) == deck.pick())
  }
}
