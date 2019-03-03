import exceptions.EmptyPileException
import models.{Deck, SpanishCard}
import org.scalatest.FunSuite

class DeckTest extends FunSuite {

  val aceOfGolds = new SpanishCard(1, "golds")
  val twoOfCups = new SpanishCard(2, "cups")

  test("givenTwoUpturnCards_whenBuildingADeckWithThem_thenCardsInTheDeckAreDownturned") {
    val waste = new Deck(aceOfGolds.upturn() :: twoOfCups.upturn() :: Nil)
    assert(aceOfGolds.downturn()::twoOfCups.downturn()::Nil == waste.cards())
  }

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
