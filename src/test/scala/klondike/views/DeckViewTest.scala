package klondike.views

import klondike.models.{Deck, SpanishCard}
import org.scalatest.FunSuite

class DeckViewTest extends FunSuite with PileViewBehaviors {

  private val aceOfGolds = new SpanishCard(1, "golds")
  private val twoOfCups = new SpanishCard(2, "cups")
  private val threeOfSwords = new SpanishCard(3, "swords")
  private val fourOfClubs = new SpanishCard(4, "clubs")
  private val kingOfGolds = new SpanishCard(SpanishCard.MAX_VALUE, "golds")

  testsFor(checkView(new Deck(Nil), new DeckView(SpanishCardView), "   ", "[     ]", "withNoCards"))
  testsFor(checkView(new Deck(aceOfGolds::Nil), new DeckView(SpanishCardView), "   ", "[XXXXX]", "withOneCard"))
  testsFor(checkView(new Deck(aceOfGolds::twoOfCups::Nil), new DeckView(SpanishCardView), "  |", "[XXXXX]", "withTwoCards"))
  testsFor(checkView(new Deck(aceOfGolds::twoOfCups::threeOfSwords::Nil), new DeckView(SpanishCardView), " ||", "[XXXXX]", "withThreeCards"))
  testsFor(checkView(new Deck(aceOfGolds::twoOfCups::threeOfSwords::fourOfClubs:: Nil), new DeckView(SpanishCardView), "|||", "[XXXXX]", "withFourCards"))
  testsFor(checkView(new Deck(aceOfGolds::twoOfCups::threeOfSwords::fourOfClubs:: kingOfGolds::Nil), new DeckView(SpanishCardView), "|||", "[XXXXX]", "withMoreThanFourCards"))

}
