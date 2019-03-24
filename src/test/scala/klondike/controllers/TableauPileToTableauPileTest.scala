package klondike.controllers

import klondike.exceptions.{EmptyPileException, InvalidMoveException}
import klondike.models.TableauPile
import klondike.test_utils.TestModels._
import klondike.views.SpanishGameFactory
import org.scalatest.FunSuite

class TableauPileToTableauPileTest extends FunSuite {

  private val movementFactory = new MovementFactory(SpanishGameFactory.tableauPileValidator)

  test("givenTwoEmptyTableauPiles_whenMovingFromTableauPileToTableauPile_thenExceptionIsThrown") {
    intercept[EmptyPileException] {
      movementFactory.betweenTableauPiles(1)(emptyTableauPile, emptyTableauPile)
    }
  }

  test("givenTwoEmptyTableauPiles_whenAttemptingToMoveANegativeNumberOfCards_thenExceptionIsThrown") {
    intercept[InvalidMoveException] {
      movementFactory.betweenTableauPiles(-1)(emptyTableauPile, emptyTableauPile)
    }
  }

  test("givenTwoEmptyTableauPiles_whenMovingZeroCardsFromBetweenThem_thenNoExceptionIsThrown") {
    val move = movementFactory.betweenTableauPiles(0)
    val (sourceAfterMove, destinationAfterMove) = move(emptyTableauPile, emptyTableauPile)
    assert(sourceAfterMove.empty)
    assert(destinationAfterMove.empty)
  }

  test("givenATableauPileWithAKing_whenMovingToAnEmptyTableauPile_thenTheFirstTableauIsEmptyAndTheOtherTableauPileContainsTheCard") {
    val move = movementFactory.betweenTableauPiles(1)
    val (sourceAfterMove, destinationAfterMove) = move(tableauPileWithCard(kingOfSwords), emptyTableauPile)
    assert(sourceAfterMove.empty)
    assert(kingOfSwords :: Nil == destinationAfterMove.cards)
  }

  test("givenATableauPileWithACardDifferentFromAKing_whenMovingToAnEmptyTableauPile_thenExceptionIsThrown") {
    val move = movementFactory.betweenTableauPiles(1)
    intercept[InvalidMoveException] {
      move(tableauPileWithCard(aceOfGolds), emptyTableauPile)
    }
  }

  test("givenATableauPileWithOneCard_whenMovingToATableauPileThatCanReceiveThatCard_thenNewTableauPileIsEmptyAndTableauPilesContainsTheCards") {
    val move = movementFactory.betweenTableauPiles(1)
    val (sourceAfterMove, destinationAfterMove) = move(tableauPileWithCard(aceOfGolds), tableauPileWithCard(twoOfClubsSpanish))
    assert(sourceAfterMove.empty)
    assert(aceOfGolds :: twoOfClubsSpanish :: Nil == destinationAfterMove.cards)
  }

  test("givenATableauPileWithACard_whenMovingToATableauPileThatCannotReceiveThatCard_thenExceptionIsThrown") {
    val move = movementFactory.betweenTableauPiles(1)
    intercept[InvalidMoveException] {
      move(tableauPileWithCard(twoOfClubsSpanish), tableauPileWithCard(aceOfGolds))
    }
  }

  test("givenATableauPileWithThreeCards_whenMovingThemToATableauPileThatCanReceiveThatCard_thenNewTableauPileIsEmptyAndTableauPilesContainsTheCards") {
    val source = new TableauPile(aceOfGolds :: twoOfClubsSpanish :: threeOfSwords :: Nil)
    val destination = tableauPileWithCard(fourOfCups)
    val move = movementFactory.betweenTableauPiles(3)
    val (sourceAfterMove, destinationAfterMove) = move(source, destination)
    assert(sourceAfterMove.empty)
    assert(source.cards ::: destination.cards == destinationAfterMove.cards)
  }

  test("givenATableauPileWithThreeCards_whenMovingThemToATableauPileThatCannotReceiveThatCard_thenExceptionIsThrown") {
    val source = new TableauPile(aceOfGolds :: twoOfClubsSpanish :: threeOfSwords :: Nil)
    val destination = tableauPileWithCard(kingOfSwords)
    val move = movementFactory.betweenTableauPiles(3)
    intercept[InvalidMoveException] {
      move(source, destination)
    }
  }
}
