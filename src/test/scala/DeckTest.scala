import exceptions.EmptyPileException
import models.{Deck, SpanishCard}
import org.scalatest.FunSuite

class DeckTest extends FunSuite {

  val aceOfGolds = new SpanishCard(1, "golds")

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
