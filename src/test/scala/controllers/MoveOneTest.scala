package controllers

import models._
import org.scalatest.FunSuite

class MoveOneTest extends FunSuite with MoveOneBehaviors {

  private val emptyDeck = new Deck(Nil)
  private val emptyWaste = new Waste(Nil)
  private val emptyFoundation = new Foundation(Nil)
  private val emptyTableauPile = new TableauPile(Nil)

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

  testsFor(emptySource(emptyDeck, _, deckToWaste))
  testsFor(emptySource(emptyWaste, _, wasteToFoundation))
  testsFor(emptySource(emptyWaste, _, wasteToTableauPile))
  testsFor(emptySource(emptyTableauPile, _, tableauPileToFoundation))
  testsFor(emptySource(emptyFoundation, _, foundationToTableauPile))

  testsFor(emptyDestinationThatCanAcceptTheCard(new Deck(twoOfClubs :: Nil), emptyWaste, deckToWaste))
  testsFor(emptyDestinationThatCanAcceptTheCard(new Waste(aceOfGolds :: Nil), emptyFoundation, wasteToFoundation))
  testsFor(emptyDestinationThatCanAcceptTheCard(new Waste(kingOfSwords :: Nil), emptyTableauPile, wasteToTableauPile))
  testsFor(emptyDestinationThatCanAcceptTheCard(new TableauPile(aceOfGolds :: Nil), emptyFoundation, tableauPileToFoundation))
  testsFor(emptyDestinationThatCanAcceptTheCard(new Foundation(kingOfSwords :: Nil), emptyTableauPile, foundationToTableauPile))

  testsFor(emptyDestinationThatCannotAcceptTheCard(new Waste(twoOfClubs :: Nil), emptyFoundation, wasteToFoundation))
  testsFor(emptyDestinationThatCannotAcceptTheCard(new Waste(aceOfGolds :: Nil), emptyTableauPile, wasteToTableauPile))
  testsFor(emptyDestinationThatCannotAcceptTheCard(new TableauPile(kingOfSwords :: Nil), emptyFoundation, tableauPileToFoundation))
  testsFor(emptyDestinationThatCannotAcceptTheCard(new Foundation(threeOfCups :: Nil), emptyTableauPile, foundationToTableauPile))

  testsFor(nonEmptyDestinationThatCanAcceptTheCard(new Deck(twoOfClubs :: Nil), new Waste(kingOfSwords :: Nil), deckToWaste))
  testsFor(nonEmptyDestinationThatCanAcceptTheCard(new Waste(twoOfGolds :: Nil), new Foundation(aceOfGolds :: Nil), wasteToFoundation))
  testsFor(nonEmptyDestinationThatCanAcceptTheCard(new Waste(twoOfClubs :: Nil), new TableauPile(threeOfCups :: Nil), wasteToTableauPile))
  testsFor(nonEmptyDestinationThatCanAcceptTheCard(new TableauPile(twoOfGolds :: Nil), new Foundation(aceOfGolds :: Nil), tableauPileToFoundation))
  testsFor(nonEmptyDestinationThatCanAcceptTheCard(new Foundation(twoOfGolds :: Nil), new TableauPile(threeOfCups :: Nil), foundationToTableauPile))

  testsFor(nonEmptyDestinationThatCannotAcceptTheCard(new Waste(twoOfClubs :: Nil), new Foundation(aceOfGolds :: Nil), wasteToFoundation))
  testsFor(nonEmptyDestinationThatCannotAcceptTheCard(new Waste(aceOfGolds :: Nil), new TableauPile(threeOfCups :: Nil), wasteToTableauPile))
  testsFor(nonEmptyDestinationThatCannotAcceptTheCard(new TableauPile(kingOfGolds :: Nil), new Foundation(aceOfGolds :: Nil), tableauPileToFoundation))
  testsFor(nonEmptyDestinationThatCannotAcceptTheCard(new Foundation(aceOfGolds :: Nil), new TableauPile(twoOfGolds :: Nil), foundationToTableauPile))
}
