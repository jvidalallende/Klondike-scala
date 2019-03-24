package klondike.views

import klondike.test_utils.TestModels._
import klondike.models.{Deck, Foundation, Waste}
import org.scalatest.FunSuite

class OnlyTopCardPileViewTest extends FunSuite with OnlyTopCardPileViewBehaviors {

  testsFor(checkView(emptyDeck, SpanishCardView, "[         ]", "withNoSpanishCards"))
  testsFor(checkView(deckWithCard(aceOfGolds), SpanishCardView, "[XXXXXXXXX]", "withOneSpanishCard"))
  testsFor(checkView(new Deck(aceOfGolds :: twoOfCups :: Nil), SpanishCardView, "[XXXXXXXXX]", "withMoreThanOneSpanishCard"))

  testsFor(checkView(emptyWaste, SpanishCardView, "[         ]", "withNoSpanishCards"))
  testsFor(checkView(wasteWithCard(aceOfGolds), SpanishCardView, "[ 1 GOLDS ]", "withOneSpanishCard"))
  testsFor(checkView(new Waste(aceOfGolds :: twoOfCups :: Nil), SpanishCardView, "[ 1 GOLDS ]", "withMoreThanOneSpanishCard"))

  testsFor(checkView(emptyFoundation, SpanishCardView, "[         ]", "withNoSpanishCards"))
  testsFor(checkView(foundationWithCard(aceOfGolds), SpanishCardView, "[ 1 GOLDS ]", "withOneSpanishCard"))
  testsFor(checkView(new Foundation(twoOfGolds :: aceOfGolds :: Nil), SpanishCardView, "[ 2 GOLDS ]", "withMoreThanOneSpanishCard"))

  testsFor(checkView(emptyDeck, FrenchCardView, "[           ]", "withNoFrenchCards"))
  testsFor(checkView(deckWithCard(aceOfHearts), FrenchCardView, "[XXXXXXXXXXX]", "withOneFrenchCard"))
  testsFor(checkView(new Deck(aceOfHearts :: twoOfCups :: Nil), FrenchCardView, "[XXXXXXXXXXX]", "withMoreThanOneFrenchCard"))

  testsFor(checkView(emptyWaste, FrenchCardView, "[           ]", "withNoFrenchCards"))
  testsFor(checkView(wasteWithCard(aceOfHearts), FrenchCardView, "[ A HEARTS  ]", "withOneFrenchCard"))
  testsFor(checkView(new Waste(aceOfHearts :: twoOfCups :: Nil), FrenchCardView, "[ A HEARTS  ]", "withMoreThanOneFrenchCard"))

  testsFor(checkView(emptyFoundation, FrenchCardView, "[           ]", "withNoFrenchCards"))
  testsFor(checkView(foundationWithCard(aceOfHearts), FrenchCardView, "[ A HEARTS  ]", "withOneFrenchCard"))
  testsFor(checkView(new Foundation(twoOfHearts :: aceOfHearts :: Nil), FrenchCardView, "[ 2 HEARTS  ]", "withMoreThanOneFrenchCard"))
}
