package klondike.controllers

import klondike.test_utils.TestModels._
import klondike.views.SpanishGameFactory
import org.scalatest.FunSuite

class MoveOneTest extends FunSuite with MoveOneBehaviors {

  private val movementFactory = new MovementFactory(SpanishGameFactory.tableauPileValidator)

  // Empty source
  testsFor(emptySource(emptyDeck, _, movementFactory.deckToWaste))
  testsFor(emptySource(emptyWaste, _, movementFactory.toFoundation))
  testsFor(emptySource(emptyWaste, _, movementFactory.toTableauPile))
  testsFor(emptySource(emptyTableauPile, _, movementFactory.toFoundation))
  testsFor(emptySource(emptyFoundation, _, movementFactory.toTableauPile))

  // Empty destination, can accept
  testsFor(destinationThatCanAcceptTheCard(deckWithCard(twoOfClubs), emptyWaste, movementFactory.deckToWaste))
  testsFor(destinationThatCanAcceptTheCard(wasteWithCard(aceOfGolds), emptyFoundation, movementFactory.toFoundation))
  testsFor(destinationThatCanAcceptTheCard(wasteWithCard(kingOfSwords), emptyTableauPile, movementFactory.toTableauPile))
  testsFor(destinationThatCanAcceptTheCard(tableauPileWithCard(aceOfGolds), emptyFoundation, movementFactory.toFoundation))
  testsFor(destinationThatCanAcceptTheCard(foundationWithCard(kingOfSwords), emptyTableauPile, movementFactory.toTableauPile))

  // Empty destination, cannot accept
  testsFor(destinationThatCannotAcceptTheCard(wasteWithCard(twoOfClubs), emptyFoundation, movementFactory.toFoundation))
  testsFor(destinationThatCannotAcceptTheCard(wasteWithCard(aceOfGolds), emptyTableauPile, movementFactory.toTableauPile))
  testsFor(destinationThatCannotAcceptTheCard(tableauPileWithCard(kingOfSwords), emptyFoundation, movementFactory.toFoundation))
  testsFor(destinationThatCannotAcceptTheCard(foundationWithCard(threeOfCups), emptyTableauPile, movementFactory.toTableauPile))

  // Filled destination, can accept
  testsFor(destinationThatCanAcceptTheCard(deckWithCard(twoOfClubs), wasteWithCard(kingOfSwords), movementFactory.deckToWaste))
  testsFor(destinationThatCanAcceptTheCard(wasteWithCard(twoOfGolds), foundationWithCard(aceOfGolds), movementFactory.toFoundation))
  testsFor(destinationThatCanAcceptTheCard(wasteWithCard(twoOfClubs), tableauPileWithCard(threeOfCups), movementFactory.toTableauPile))
  testsFor(destinationThatCanAcceptTheCard(tableauPileWithCard(twoOfGolds), foundationWithCard(aceOfGolds), movementFactory.toFoundation))
  testsFor(destinationThatCanAcceptTheCard(foundationWithCard(twoOfGolds), tableauPileWithCard(threeOfCups), movementFactory.toTableauPile))

  // Filled destination, cannot accept
  testsFor(destinationThatCannotAcceptTheCard(wasteWithCard(twoOfClubs), foundationWithCard(aceOfGolds), movementFactory.toFoundation))
  testsFor(destinationThatCannotAcceptTheCard(wasteWithCard(aceOfGolds), tableauPileWithCard(threeOfCups), movementFactory.toTableauPile))
  testsFor(destinationThatCannotAcceptTheCard(tableauPileWithCard(kingOfGolds), foundationWithCard(aceOfGolds), movementFactory.toFoundation))
  testsFor(destinationThatCannotAcceptTheCard(foundationWithCard(aceOfGolds), tableauPileWithCard(twoOfGolds), movementFactory.toTableauPile))
}
