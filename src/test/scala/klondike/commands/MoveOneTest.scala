package klondike.commands

import klondike.test_utils.TestModels._
import klondike.views.{FrenchGameFactory, SpanishGameFactory}
import org.scalatest.FunSuite

class MoveOneTest extends FunSuite with MoveOneBehaviors {

  private val spanishMovements = new MovementFactory(SpanishGameFactory.tableauPileValidator)
  private val frenchMovements = new MovementFactory(FrenchGameFactory.tableauPileValidator)

  // Spanish card ***************************************
  // Empty source
  testsFor(emptySource("SpanishCard", emptyDeck, _, spanishMovements.deckToWaste))
  testsFor(emptySource("SpanishCard", emptyWaste, _, spanishMovements.toFoundation))
  testsFor(emptySource("SpanishCard", emptyWaste, _, spanishMovements.toTableauPile))
  testsFor(emptySource("SpanishCard", emptyTableauPile, _, spanishMovements.toFoundation))
  testsFor(emptySource("SpanishCard", emptyFoundation, _, spanishMovements.toTableauPile))

  // Empty destination, can accept
  testsFor(destinationThatCanAcceptTheCard("SpanishCard", deckWithCard(twoOfClubsSpanish), emptyWaste, spanishMovements.deckToWaste))
  testsFor(destinationThatCanAcceptTheCard("SpanishCard", wasteWithCard(aceOfGolds), emptyFoundation, spanishMovements.toFoundation))
  testsFor(destinationThatCanAcceptTheCard("SpanishCard", wasteWithCard(kingOfSwords), emptyTableauPile, spanishMovements.toTableauPile))
  testsFor(destinationThatCanAcceptTheCard("SpanishCard", tableauPileWithCard(aceOfGolds), emptyFoundation, spanishMovements.toFoundation))
  testsFor(destinationThatCanAcceptTheCard("SpanishCard", foundationWithCard(kingOfSwords), emptyTableauPile, spanishMovements.toTableauPile))

  // Empty destination, cannot accept
  testsFor(destinationThatCannotAcceptTheCard("SpanishCard", wasteWithCard(twoOfClubsSpanish), emptyFoundation, spanishMovements.toFoundation))
  testsFor(destinationThatCannotAcceptTheCard("SpanishCard", wasteWithCard(aceOfGolds), emptyTableauPile, spanishMovements.toTableauPile))
  testsFor(destinationThatCannotAcceptTheCard("SpanishCard", tableauPileWithCard(kingOfSwords), emptyFoundation, spanishMovements.toFoundation))
  testsFor(destinationThatCannotAcceptTheCard("SpanishCard", foundationWithCard(threeOfCups), emptyTableauPile, spanishMovements.toTableauPile))

  // Filled destination, can accept
  testsFor(destinationThatCanAcceptTheCard("SpanishCard", deckWithCard(twoOfClubsSpanish), wasteWithCard(kingOfSwords), spanishMovements.deckToWaste))
  testsFor(destinationThatCanAcceptTheCard("SpanishCard", wasteWithCard(twoOfGolds), foundationWithCard(aceOfGolds), spanishMovements.toFoundation))
  testsFor(destinationThatCanAcceptTheCard("SpanishCard", wasteWithCard(twoOfClubsSpanish), tableauPileWithCard(threeOfCups), spanishMovements.toTableauPile))
  testsFor(destinationThatCanAcceptTheCard("SpanishCard", tableauPileWithCard(twoOfGolds), foundationWithCard(aceOfGolds), spanishMovements.toFoundation))
  testsFor(destinationThatCanAcceptTheCard("SpanishCard", foundationWithCard(twoOfGolds), tableauPileWithCard(threeOfCups), spanishMovements.toTableauPile))

  // Filled destination, cannot accept
  testsFor(destinationThatCannotAcceptTheCard("SpanishCard", wasteWithCard(twoOfClubsSpanish), foundationWithCard(aceOfGolds), spanishMovements.toFoundation))
  testsFor(destinationThatCannotAcceptTheCard("SpanishCard", wasteWithCard(aceOfGolds), tableauPileWithCard(threeOfCups), spanishMovements.toTableauPile))
  testsFor(destinationThatCannotAcceptTheCard("SpanishCard", tableauPileWithCard(kingOfGolds), foundationWithCard(aceOfGolds), spanishMovements.toFoundation))
  testsFor(destinationThatCannotAcceptTheCard("SpanishCard", foundationWithCard(aceOfGolds), tableauPileWithCard(twoOfGolds), spanishMovements.toTableauPile))

  // French card ***************************************
  // Empty source
  testsFor(emptySource("FrenchCard", emptyDeck, _, frenchMovements.deckToWaste))
  testsFor(emptySource("FrenchCard", emptyWaste, _, frenchMovements.toFoundation))
  testsFor(emptySource("FrenchCard", emptyWaste, _, frenchMovements.toTableauPile))
  testsFor(emptySource("FrenchCard", emptyTableauPile, _, frenchMovements.toFoundation))
  testsFor(emptySource("FrenchCard", emptyFoundation, _, frenchMovements.toTableauPile))

  // Empty destination, can accept
  testsFor(destinationThatCanAcceptTheCard("FrenchCard", deckWithCard(twoOfClubsFrench), emptyWaste, frenchMovements.deckToWaste))
  testsFor(destinationThatCanAcceptTheCard("FrenchCard", wasteWithCard(aceOfHearts), emptyFoundation, frenchMovements.toFoundation))
  testsFor(destinationThatCanAcceptTheCard("FrenchCard", wasteWithCard(kingOfSpades), emptyTableauPile, frenchMovements.toTableauPile))
  testsFor(destinationThatCanAcceptTheCard("FrenchCard", tableauPileWithCard(aceOfHearts), emptyFoundation, frenchMovements.toFoundation))
  testsFor(destinationThatCanAcceptTheCard("FrenchCard", foundationWithCard(kingOfSpades), emptyTableauPile, frenchMovements.toTableauPile))

  // Empty destination, cannot accept
  testsFor(destinationThatCannotAcceptTheCard("FrenchCard", wasteWithCard(twoOfClubsFrench), emptyFoundation, frenchMovements.toFoundation))
  testsFor(destinationThatCannotAcceptTheCard("FrenchCard", wasteWithCard(aceOfHearts), emptyTableauPile, frenchMovements.toTableauPile))
  testsFor(destinationThatCannotAcceptTheCard("FrenchCard", tableauPileWithCard(kingOfSpades), emptyFoundation, frenchMovements.toFoundation))
  testsFor(destinationThatCannotAcceptTheCard("FrenchCard", foundationWithCard(threeOfDiamonds), emptyTableauPile, frenchMovements.toTableauPile))

  // Filled destination, can accept
  testsFor(destinationThatCanAcceptTheCard("FrenchCard", deckWithCard(twoOfClubsFrench), wasteWithCard(kingOfSpades), frenchMovements.deckToWaste))
  testsFor(destinationThatCanAcceptTheCard("FrenchCard", wasteWithCard(twoOfHearts), foundationWithCard(aceOfHearts), frenchMovements.toFoundation))
  testsFor(destinationThatCanAcceptTheCard("FrenchCard", wasteWithCard(twoOfClubsFrench), tableauPileWithCard(threeOfDiamonds), frenchMovements.toTableauPile))
  testsFor(destinationThatCanAcceptTheCard("FrenchCard", tableauPileWithCard(twoOfHearts), foundationWithCard(aceOfHearts), frenchMovements.toFoundation))
  testsFor(destinationThatCanAcceptTheCard("FrenchCard", foundationWithCard(twoOfClubsFrench), tableauPileWithCard(threeOfDiamonds), frenchMovements.toTableauPile))
  testsFor(destinationThatCanAcceptTheCard("FrenchCard", foundationWithCard(twoOfDiamonds), tableauPileWithCard(threeOfSpades), frenchMovements.toTableauPile))
  testsFor(destinationThatCanAcceptTheCard("FrenchCard", foundationWithCard(aceOfSpades), tableauPileWithCard(twoOfDiamonds), frenchMovements.toTableauPile))

  // Filled destination, cannot accept
  testsFor(destinationThatCannotAcceptTheCard("FrenchCard", wasteWithCard(twoOfClubsFrench), foundationWithCard(aceOfHearts), frenchMovements.toFoundation))
  testsFor(destinationThatCannotAcceptTheCard("FrenchCard", wasteWithCard(aceOfHearts), tableauPileWithCard(threeOfDiamonds), frenchMovements.toTableauPile))
  testsFor(destinationThatCannotAcceptTheCard("FrenchCard", tableauPileWithCard(kingOfHearts), foundationWithCard(aceOfHearts), frenchMovements.toFoundation))
  testsFor(destinationThatCannotAcceptTheCard("FrenchCard", foundationWithCard(aceOfHearts), tableauPileWithCard(twoOfHearts), frenchMovements.toTableauPile))
}
