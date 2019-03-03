import exceptions.EmptyPileException
import models.{Deck, SpanishCard}
import org.scalatest.FunSuite

/* This suite uses the Deck concrete class, but is testing the functionality for Pile
   class only, so that suites for concrete classes only contain their specific additions. */
class PileTest extends FunSuite {

  private val aceOfGolds = new SpanishCard(1, "golds")
  private val twoOfClubs = new SpanishCard(2, "clubs")
  private val kingOfSpades = new SpanishCard(12, "Spades")

  test("givenOneEmptyDeck_whenPickingFromIt_thenRaisesException") {
    intercept[EmptyPileException] {
      new Deck(Nil).pick()
    }
  }

  test("givenOneEmptyDeck_whenCheckingIfItIsEmpty_thenItIsEmpty") {
    assert(new Deck(Nil).empty())
  }

  test("givenOneDeckWithOneCard_whenCheckingIfItIsEmpty_thenItIsNotEmpty") {
    assert(!new Deck(aceOfGolds :: Nil).empty())
  }

  test("givenADeck_whenComparedToAListOfCards_thenTheyAreNotEqual") {
    val cards = List(aceOfGolds.upturn(), twoOfClubs, kingOfSpades)
    assert(new Deck(cards) != cards)
  }
}
