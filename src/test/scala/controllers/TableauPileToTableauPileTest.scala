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


  test("givenTwoEmptyTableauPiles_whenMovingFromTableauPileToTableauPile_thenExceptionIsThrown") {
    intercept[EmptyPileException] {
      MovementFactory.tableauPileToTableauPile(1)(emptyTableauPile, emptyTableauPile)
    }
  }

  test("givenTwoEmptyTableauPiles_whenAttemptingToMoveANegativeNumberOfCards_thenExceptionIsThrown") {
    intercept[InvalidMoveException] {
      MovementFactory.tableauPileToTableauPile(-1)(emptyTableauPile, emptyTableauPile)
    }
  }

  test("givenTwoEmptyTableauPiles_whenMovingZeroCardsFromBetweenThem_thenNoExceptionIsThrown") {
    val move = MovementFactory.tableauPileToTableauPile(0)
    val (sourceAfterMove, destinationAfterMove) = move(emptyTableauPile, emptyTableauPile)
    assert(sourceAfterMove.empty())
    assert(destinationAfterMove.empty())
  }

  test("givenATableauPileWithAKing_whenMovingToAnEmptyTableauPile_thenTheFirstTableauIsEmptyAndTheOtherTableauPileContainsTheCard") {
    val move = MovementFactory.tableauPileToTableauPile(1)
    val (sourceAfterMove, destinationAfterMove) = move(new TableauPile(kingOfSwords :: Nil), emptyTableauPile)
    assert(sourceAfterMove.empty())
    assert(kingOfSwords :: Nil == destinationAfterMove.cards())
  }

  test("givenATableauPileWithACardDifferentFromAKing_whenMovingToAnEmptyTableauPile_thenExceptionIsThrown") {
    val move = MovementFactory.tableauPileToTableauPile(1)
    intercept[InvalidMoveException] {
      move(new TableauPile(aceOfGolds :: Nil), emptyTableauPile)
    }
  }

  test("givenATableauPileWithOneCard_whenMovingToATableauPileThatCanReceiveThatCard_thenNewTableauPileIsEmptyAndTableauPilesContainsTheCards") {
    val move = MovementFactory.tableauPileToTableauPile(1)
    val (sourceAfterMove, destinationAfterMove) = move(new TableauPile(aceOfGolds :: Nil), new TableauPile(twoOfClubs :: Nil))
    assert(sourceAfterMove.empty())
    assert(aceOfGolds :: twoOfClubs :: Nil == destinationAfterMove.cards())
  }

  test("givenATableauPileWithACard_whenMovingToATableauPileThatCannotReceiveThatCard_thenExceptionIsThrown") {
    val move = MovementFactory.tableauPileToTableauPile(1)
    intercept[InvalidMoveException] {
      move(new TableauPile(twoOfClubs :: Nil), new TableauPile(aceOfGolds :: Nil))
    }
  }

  test("givenATableauPileWithThreeCards_whenMovingThemToATableauPileThatCanReceiveThatCard_thenNewTableauPileIsEmptyAndTableauPilesContainsTheCards") {
    val source = new TableauPile(aceOfGolds :: twoOfClubs :: threeOfSwords :: Nil)
    val destination = new TableauPile(fourOfCups :: Nil)
    val move = MovementFactory.tableauPileToTableauPile(3)
    val (sourceAfterMove, destinationAfterMove) = move(source, destination)
    assert(sourceAfterMove.empty())
    assert(source.cards() ::: destination.cards() == destinationAfterMove.cards())
  }

  test("givenATableauPileWithThreeCards_whenMovingThemToATableauPileThatCannotReceiveThatCard_thenExceptionIsThrown") {
    val source = new TableauPile(aceOfGolds :: twoOfClubs :: threeOfSwords :: Nil)
    val destination = new TableauPile(kingOfSwords :: Nil)
    val move = MovementFactory.tableauPileToTableauPile(3)
    intercept[InvalidMoveException] {
      move(source, destination)
    }
  }
}
