package controllers

import exceptions.{EmptyPileException, InvalidMoveException}
import models.{SpanishCard, TableauPile}
import org.scalatest.FunSuite

class TableauPileToTableauPileTest extends FunSuite {

  private val emptyTableauPile = new TableauPile(Nil)

  private val aceOfGolds = new SpanishCard(1, "golds", true)
  private val twoOfClubs = new SpanishCard(2, "clubs", true)
  private val threeOfSwords = new SpanishCard(3, "swords", true)
  private val fourOfCups = new SpanishCard(4, "cups", true)
  private val kingOfSwords = new SpanishCard(SpanishCard.MAX_VALUE, "swords", true)

  private val move = new MovementFactory().tableauPileToTableauPile()

  test("givenTwoEmptyTableauPiles_whenMovingFromTableauPileToTableauPile_thenExceptionIsThrown") {
    intercept[EmptyPileException] {
      move(emptyTableauPile, emptyTableauPile, 1)
    }
  }

  test("givenTwoEmptyTableauPiles_whenAttemptingToMoveANegativeNumberOfCards_thenExceptionIsThrown") {
    intercept[InvalidMoveException] {
      move(emptyTableauPile, emptyTableauPile, -1)
    }
  }

  test("givenTwoEmptyTableauPiles_whenMovingZeroCardsFromBetweenThem_thenNoExceptionIsThrown") {
    val (sourceAfterMove, destinationAfterMove) = move(emptyTableauPile, emptyTableauPile, 0)
    assert(sourceAfterMove.empty())
    assert(destinationAfterMove.empty())
  }

  test("givenATableauPileWithAKing_whenMovingToAnEmptyTableauPile_thenTheFirstTableauIsEmptyAndTheOtherTableauPileContainsTheCard") {
    val source = new TableauPile(kingOfSwords :: Nil)
    val (sourceAfterMove, destinationAfterMove) = move(source, emptyTableauPile, 1)
    assert(sourceAfterMove.empty())
    assert(kingOfSwords :: Nil == destinationAfterMove.cards())
  }

  test("givenATableauPileWithACardDifferentFromAKing_whenMovingToAnEmptyTableauPile_thenExceptionIsThrown") {
    val source = new TableauPile(aceOfGolds :: Nil)
    intercept[InvalidMoveException] {
      move(source, emptyTableauPile, 1)
    }
  }

  test("givenATableauPileWithOneCard_whenMovingToATableauPileThatCanReceiveThatCard_thenNewTableauPileIsEmptyAndTableauPilesContainsTheCards") {
    val source = new TableauPile(aceOfGolds :: Nil)
    val destination = new TableauPile(twoOfClubs :: Nil)
    val (sourceAfterMove, destinationAfterMove) = move(source, destination, 1)
    assert(sourceAfterMove.empty())
    assert(aceOfGolds :: destination.cards() == destinationAfterMove.cards())
  }

  test("givenATableauPileWithACard_whenMovingToATableauPileThatCannotReceiveThatCard_thenExceptionIsThrown") {
    val source = new TableauPile(twoOfClubs :: Nil)
    val destination = new TableauPile(aceOfGolds :: Nil)
    intercept[InvalidMoveException] {
      move(source, destination, 1)
    }
  }

  test("givenATableauPileWithThreeCards_whenMovingThemToATableauPileThatCanReceiveThatCard_thenNewTableauPileIsEmptyAndTableauPilesContainsTheCards") {
    val source = new TableauPile(aceOfGolds :: twoOfClubs :: threeOfSwords :: Nil)
    val destination = new TableauPile(fourOfCups :: Nil)
    val (sourceAfterMove, destinationAfterMove) = move(source, destination, 3)
    assert(sourceAfterMove.empty())
    assert(source.cards() ::: destination.cards() == destinationAfterMove.cards())
  }

  test("givenATableauPileWithThreeCards_whenMovingThemToATableauPileThatCannotReceiveThatCard_thenExceptionIsThrown") {
    val source = new TableauPile(aceOfGolds :: twoOfClubs :: threeOfSwords :: Nil)
    val destination = new TableauPile(kingOfSwords :: Nil)
    intercept[InvalidMoveException] {
      move(source, destination, 3)
    }
  }
}
