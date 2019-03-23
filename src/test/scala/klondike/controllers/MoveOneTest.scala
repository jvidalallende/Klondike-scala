package klondike.controllers

import klondike.models._
import klondike.views.SpanishGameFactory
import org.scalatest.FunSuite

class MoveOneTest extends FunSuite with MoveOneBehaviors {

  private val emptyDeck = new Deck(Nil, "EmptyDeck")
  private val emptyWaste = new Waste(Nil, "EmptyWaste")
  private val emptyFoundation = new Foundation(Nil, "EmptyFoundation")
  private val emptyTableauPile = new TableauPile(Nil, "EmptyTableauPile")

  private val aceOfGolds = new SpanishCard(SpanishCard.MIN_VALUE, "golds", true)
  private val twoOfGolds = new SpanishCard(2, "golds", true)
  private val twoOfClubs = new SpanishCard(2, "clubs", true)
  private val threeOfCups = new SpanishCard(3, "cups", true)
  private val kingOfGolds = new SpanishCard(SpanishCard.MAX_VALUE, "golds", true)
  private val kingOfSwords = new SpanishCard(SpanishCard.MAX_VALUE, "swords", true)

  private val movementBuilder = new MovementBuilder(SpanishGameFactory.tableauPileValidator)

  // Empty source
  testsFor(emptySource(emptyDeck, _, movementBuilder.deckToWaste))
  testsFor(emptySource(emptyWaste, _, movementBuilder.toFoundation))
  testsFor(emptySource(emptyWaste, _, movementBuilder.toTableauPile))
  testsFor(emptySource(emptyTableauPile, _, movementBuilder.toFoundation))
  testsFor(emptySource(emptyFoundation, _, movementBuilder.toTableauPile))

  // Empty destination, can accept
  testsFor(destinationThatCanAcceptTheCard(new Deck(twoOfClubs :: Nil), emptyWaste, movementBuilder.deckToWaste))
  testsFor(destinationThatCanAcceptTheCard(new Waste(aceOfGolds :: Nil), emptyFoundation, movementBuilder.toFoundation))
  testsFor(destinationThatCanAcceptTheCard(new Waste(kingOfSwords :: Nil), emptyTableauPile, movementBuilder.toTableauPile))
  testsFor(destinationThatCanAcceptTheCard(new TableauPile(aceOfGolds :: Nil), emptyFoundation, movementBuilder.toFoundation))
  testsFor(destinationThatCanAcceptTheCard(new Foundation(kingOfSwords :: Nil), emptyTableauPile, movementBuilder.toTableauPile))

  // Empty destination, cannot accept
  testsFor(destinationThatCannotAcceptTheCard(new Waste(twoOfClubs :: Nil), emptyFoundation, movementBuilder.toFoundation))
  testsFor(destinationThatCannotAcceptTheCard(new Waste(aceOfGolds :: Nil), emptyTableauPile, movementBuilder.toTableauPile))
  testsFor(destinationThatCannotAcceptTheCard(new TableauPile(kingOfSwords :: Nil), emptyFoundation, movementBuilder.toFoundation))
  testsFor(destinationThatCannotAcceptTheCard(new Foundation(threeOfCups :: Nil), emptyTableauPile, movementBuilder.toTableauPile))

  // Filled destination, can accept
  testsFor(destinationThatCanAcceptTheCard(new Deck(twoOfClubs :: Nil), new Waste(kingOfSwords :: Nil), movementBuilder.deckToWaste))
  testsFor(destinationThatCanAcceptTheCard(new Waste(twoOfGolds :: Nil), new Foundation(aceOfGolds :: Nil), movementBuilder.toFoundation))
  testsFor(destinationThatCanAcceptTheCard(new Waste(twoOfClubs :: Nil), new TableauPile(threeOfCups :: Nil), movementBuilder.toTableauPile))
  testsFor(destinationThatCanAcceptTheCard(new TableauPile(twoOfGolds :: Nil), new Foundation(aceOfGolds :: Nil), movementBuilder.toFoundation))
  testsFor(destinationThatCanAcceptTheCard(new Foundation(twoOfGolds :: Nil), new TableauPile(threeOfCups :: Nil), movementBuilder.toTableauPile))

  // Filled destination, cannot accept
  testsFor(destinationThatCannotAcceptTheCard(new Waste(twoOfClubs :: Nil), new Foundation(aceOfGolds :: Nil), movementBuilder.toFoundation))
  testsFor(destinationThatCannotAcceptTheCard(new Waste(aceOfGolds :: Nil), new TableauPile(threeOfCups :: Nil), movementBuilder.toTableauPile))
  testsFor(destinationThatCannotAcceptTheCard(new TableauPile(kingOfGolds :: Nil), new Foundation(aceOfGolds :: Nil), movementBuilder.toFoundation))
  testsFor(destinationThatCannotAcceptTheCard(new Foundation(aceOfGolds :: Nil), new TableauPile(twoOfGolds :: Nil), movementBuilder.toTableauPile))
}
