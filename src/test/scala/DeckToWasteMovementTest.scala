import controllers.MovementFactory
import exceptions.EmptyPileException
import models.{Deck, SpanishCard, Waste}
import org.scalatest.FunSuite

class DeckToWastementTest extends FunSuite {

  private val emptyDeck = new Deck(Nil)
  private val emptyWaste = new Waste(Nil)

  private val aceOfGolds = new SpanishCard(1, "golds")
  private val twoOfClubs = new SpanishCard(2, "clubs")
  private val kingOfSpades = new SpanishCard(12, "Spades")

  private val movements = new MovementFactory()

  test("givenEmptyDeckAndWaste_whenMovingFromDeckToWaste_thenExceptionIsThrown") {
    intercept[EmptyPileException] {
      movements.deckToWaste()(emptyDeck, emptyWaste, 1)
    }
  }

  test("givenADeckWithOneCard_whenMovingToAnEmptyWaste_thenNewDeckIsEmptyAndWasteContainsTheCard") {
    val deck = new Deck(aceOfGolds :: Nil)
    val result = movements.deckToWaste()(deck, emptyWaste, 1)
    assert(result._1.empty())
    assert(aceOfGolds.upturn() :: Nil == result._2.cards())
  }

  test("givenADeckWithOneCard_whenMovingToAFilledWaste_thenNewDeckIsEmptyAndWasteContainsTheCards") {
    val deck = new Deck(aceOfGolds :: Nil)
    val waste = new Waste(twoOfClubs.upturn() :: kingOfSpades.upturn() :: Nil)
    val result = movements.deckToWaste()(deck, waste, 1)
    assert(result._1.empty())
    assert(aceOfGolds.upturn() :: waste.cards() == result._2.cards())
  }

  test("givenADeckWithTwoCards_whenMovingToAnEmptyWaste_thenNewDeckHasOneCardAndNewWasteContainsPreviousDeckHead") {
    val deck = new Deck(aceOfGolds :: twoOfClubs :: Nil)
    val result = movements.deckToWaste()(deck, emptyWaste, 1)
    assert(twoOfClubs :: Nil == result._1.cards())
    assert(aceOfGolds.upturn() :: Nil == result._2.cards())
  }
}
