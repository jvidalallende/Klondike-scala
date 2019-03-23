package klondike.views

import klondike.test_utils.TestModels._
import klondike.models.{Deck, Foundation, SpanishCard, Waste}
import org.scalatest.FunSuite

class OnlyTopCardPileViewTest extends FunSuite with OnlyTopCardPileViewBehaviors {

  private val aceOfGolds = new SpanishCard(1, "golds")

  testsFor(checkView(emptyDeck, SpanishCardView, "[     ]", "withNoCards"))
  testsFor(checkView(deckWithCard(aceOfGolds), SpanishCardView, "[XXXXX]", "withOneCard"))
  testsFor(checkView(new Deck(aceOfGolds :: twoOfCups :: Nil), SpanishCardView, "[XXXXX]", "withMoreThanOneCard"))

  testsFor(checkView(emptyWaste, SpanishCardView, "[     ]", "withNoCards"))
  testsFor(checkView(wasteWithCard(aceOfGolds), SpanishCardView, "[ 1 GO]", "withOneCard"))
  testsFor(checkView(new Waste(aceOfGolds :: twoOfCups :: Nil), SpanishCardView, "[ 1 GO]", "withMoreThanOneCard"))

  testsFor(checkView(emptyFoundation, SpanishCardView, "[     ]", "withNoCards"))
  testsFor(checkView(foundationWithCard(aceOfGolds), SpanishCardView, "[ 1 GO]", "withOneCard"))
  testsFor(checkView(new Foundation(twoOfGolds :: aceOfGolds :: Nil), SpanishCardView, "[ 2 GO]", "withMoreThanOneCard"))
}
