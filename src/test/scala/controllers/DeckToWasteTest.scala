package controllers

import exceptions.EmptyPileException
import models.{Deck, SpanishCard, Waste}
import org.scalatest.FunSuite

class DeckToWasteTest extends FunSuite {

  private val emptyDeck = new Deck(Nil)
  private val emptyWaste = new Waste(Nil)

  private val aceOfGolds = new SpanishCard(1, "golds")
  private val twoOfClubs = new SpanishCard(2, "clubs")
  private val kingOfSwords = new SpanishCard(10, "swords")

  private val move = new MovementFactory().deckToWaste()

  test("givenEmptyDeckAndWaste_whenMovingFromDeckToWaste_thenExceptionIsThrown") {
    intercept[EmptyPileException] {
      move(emptyDeck, emptyWaste)
    }
  }

  test("givenADeckWithOneCard_whenMovingToAnEmptyWaste_thenNewDeckIsEmptyAndWasteContainsTheCard") {
    val deck = new Deck(aceOfGolds :: Nil)
    val result = move(deck, emptyWaste)
    assert(result._1.empty())
    assert(aceOfGolds.upturn() :: Nil == result._2.cards())
  }

  test("givenADeckWithOneCard_whenMovingToAFilledWaste_thenNewDeckIsEmptyAndWasteContainsTheCards") {
    val deck = new Deck(aceOfGolds :: Nil)
    val waste = new Waste(twoOfClubs.upturn() :: kingOfSwords.upturn() :: Nil)
    val result = move(deck, waste)
    assert(result._1.empty())
    assert(aceOfGolds.upturn() :: waste.cards() == result._2.cards())
  }

  test("givenADeckWithTwoCards_whenMovingToAnEmptyWaste_thenNewDeckHasOneCardAndNewWasteContainsPreviousDeckHead") {
    val deck = new Deck(aceOfGolds :: twoOfClubs :: Nil)
    val result = move(deck, emptyWaste)
    assert(twoOfClubs :: Nil == result._1.cards())
    assert(aceOfGolds.upturn() :: Nil == result._2.cards())
  }
}
