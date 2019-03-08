package controllers

import models._
import org.scalatest.FunSuite

class MoveOneTest extends FunSuite with MoveOneBehaviors {

  private val emptyDeck = new Deck(Nil, "EmptyDeck")
  private val emptyWaste = new Waste(Nil, "EmptyWaste")
  private val emptyFoundation = new Foundation(Nil, "EmptyFoundation")
  private val emptyTableauPile = new TableauPile(Nil, "EmptyTableauPile")

  private val deckToWaste = MovementFactory.deckToWaste()
  private val wasteToFoundation = MovementFactory.wasteToFoundation()
  private val wasteToTableauPile = MovementFactory.wasteToTableauPile()
  private val tableauPileToFoundation = MovementFactory.tableauPileToFoundation()
  private val foundationToTableauPile = MovementFactory.foundationToTableauPile()

  private val aceOfGolds = new SpanishCard(SpanishCard.MIN_VALUE, "golds", true)
  private val twoOfGolds = new SpanishCard(2, "golds", true)
  private val twoOfClubs = new SpanishCard(2, "clubs", true)
  private val threeOfCups = new SpanishCard(3, "cups", true)
  private val kingOfGolds = new SpanishCard(SpanishCard.MAX_VALUE, "golds", true)
  private val kingOfSwords = new SpanishCard(SpanishCard.MAX_VALUE, "swords", true)

  // Empty source
  testsFor(emptySource(emptyDeck, _, deckToWaste))
  testsFor(emptySource(emptyWaste, _, wasteToFoundation))
  testsFor(emptySource(emptyWaste, _, wasteToTableauPile))
  testsFor(emptySource(emptyTableauPile, _, tableauPileToFoundation))
  testsFor(emptySource(emptyFoundation, _, foundationToTableauPile))

  // Empty destination, can accept
  testsFor(destinationThatCanAcceptTheCard(new Deck(twoOfClubs :: Nil), emptyWaste, deckToWaste))
  testsFor(destinationThatCanAcceptTheCard(new Waste(aceOfGolds :: Nil), emptyFoundation, wasteToFoundation))
  testsFor(destinationThatCanAcceptTheCard(new Waste(kingOfSwords :: Nil), emptyTableauPile, wasteToTableauPile))
  testsFor(destinationThatCanAcceptTheCard(new TableauPile(aceOfGolds :: Nil), emptyFoundation, tableauPileToFoundation))
  testsFor(destinationThatCanAcceptTheCard(new Foundation(kingOfSwords :: Nil), emptyTableauPile, foundationToTableauPile))

  // Empty destination, cannot accept
  testsFor(destinationThatCannotAcceptTheCard(new Waste(twoOfClubs :: Nil), emptyFoundation, wasteToFoundation))
  testsFor(destinationThatCannotAcceptTheCard(new Waste(aceOfGolds :: Nil), emptyTableauPile, wasteToTableauPile))
  testsFor(destinationThatCannotAcceptTheCard(new TableauPile(kingOfSwords :: Nil), emptyFoundation, tableauPileToFoundation))
  testsFor(destinationThatCannotAcceptTheCard(new Foundation(threeOfCups :: Nil), emptyTableauPile, foundationToTableauPile))

  // Filled destination, can accept
  testsFor(destinationThatCanAcceptTheCard(new Deck(twoOfClubs :: Nil), new Waste(kingOfSwords :: Nil), deckToWaste))
  testsFor(destinationThatCanAcceptTheCard(new Waste(twoOfGolds :: Nil), new Foundation(aceOfGolds :: Nil), wasteToFoundation))
  testsFor(destinationThatCanAcceptTheCard(new Waste(twoOfClubs :: Nil), new TableauPile(threeOfCups :: Nil), wasteToTableauPile))
  testsFor(destinationThatCanAcceptTheCard(new TableauPile(twoOfGolds :: Nil), new Foundation(aceOfGolds :: Nil), tableauPileToFoundation))
  testsFor(destinationThatCanAcceptTheCard(new Foundation(twoOfGolds :: Nil), new TableauPile(threeOfCups :: Nil), foundationToTableauPile))

  // Filled destination, cannot accept
  testsFor(destinationThatCannotAcceptTheCard(new Waste(twoOfClubs :: Nil), new Foundation(aceOfGolds :: Nil), wasteToFoundation))
  testsFor(destinationThatCannotAcceptTheCard(new Waste(aceOfGolds :: Nil), new TableauPile(threeOfCups :: Nil), wasteToTableauPile))
  testsFor(destinationThatCannotAcceptTheCard(new TableauPile(kingOfGolds :: Nil), new Foundation(aceOfGolds :: Nil), tableauPileToFoundation))
  testsFor(destinationThatCannotAcceptTheCard(new Foundation(aceOfGolds :: Nil), new TableauPile(twoOfGolds :: Nil), foundationToTableauPile))
}
