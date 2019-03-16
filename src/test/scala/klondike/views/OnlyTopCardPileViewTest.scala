package klondike.views

import klondike.models.{Deck, Foundation, SpanishCard, Waste}
import org.scalatest.FunSuite

class OnlyTopCardPileViewTest extends FunSuite with PileViewBehaviors {

  private val aceOfGolds = new SpanishCard(1, "golds")
  private val twoOfGolds = new SpanishCard(2, "golds")
  private val twoOfCups = new SpanishCard(2, "cups")

  testsFor(checkView(new Deck(Nil), new OnlyTopCardPileView(SpanishCardView), "[     ]", "withNoCards"))
  testsFor(checkView(new Deck(aceOfGolds :: Nil), new OnlyTopCardPileView(SpanishCardView), "[XXXXX]", "withOneCard"))
  testsFor(checkView(new Deck(aceOfGolds :: twoOfCups :: Nil), new OnlyTopCardPileView(SpanishCardView), "[XXXXX]", "withMoreThanOneCard"))

  testsFor(checkView(new Waste(Nil), new OnlyTopCardPileView(SpanishCardView), "[     ]", "withNoCards"))
  testsFor(checkView(new Waste(aceOfGolds :: Nil), new OnlyTopCardPileView(SpanishCardView), "[ 1 GO]", "withOneCard"))
  testsFor(checkView(new Waste(aceOfGolds :: twoOfCups :: Nil), new OnlyTopCardPileView(SpanishCardView), "[ 1 GO]", "withMoreThanOneCard"))

  testsFor(checkView(new Foundation(Nil), new OnlyTopCardPileView(SpanishCardView), "[     ]", "withNoCards"))
  testsFor(checkView(new Foundation(aceOfGolds :: Nil), new OnlyTopCardPileView(SpanishCardView), "[ 1 GO]", "withOneCard"))
  testsFor(checkView(new Foundation(twoOfGolds :: aceOfGolds :: Nil), new OnlyTopCardPileView(SpanishCardView), "[ 2 GO]", "withMoreThanOneCard"))
}
