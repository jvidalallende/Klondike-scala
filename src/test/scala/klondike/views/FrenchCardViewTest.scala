package klondike.views

import klondike.models.FrenchCard
import org.scalatest.FunSuite

class FrenchCardViewTest extends FunSuite with CardViewBehaviors {

  // Whitespace
  testsFor(drawWhitespace("French", FrenchCardView, "             "))

  // Placeholder
  testsFor(drawPlaceholder("French", FrenchCardView, "[           ]"))

  // Downturned card
  testsFor(draw(FrenchCardView, new FrenchCard(10, "hearts"), "[XXXXXXXXXXX]"))

  // Single-digit values
  testsFor(draw(FrenchCardView, new FrenchCard(2, "diamonds", true), "[ 2 DIAMONDS]"))
  testsFor(draw(FrenchCardView, new FrenchCard(6, "spades", true), "[ 6 SPADES  ]"))
  testsFor(draw(FrenchCardView, new FrenchCard(7, "clubs", true), "[ 7 CLUBS   ]"))
  testsFor(draw(FrenchCardView, new FrenchCard(8, "hearts", true), "[ 8 HEARTS  ]"))
  testsFor(draw(FrenchCardView, new FrenchCard(9, "diamonds", true), "[ 9 DIAMONDS]"))

  // Double-digit values
  testsFor(draw(FrenchCardView, new FrenchCard(10, "spades", true), "[10 SPADES  ]"))
  testsFor(draw(FrenchCardView, new FrenchCard(10, "clubs", true), "[10 CLUBS   ]"))

  // Cards with letter instead of value
  testsFor(draw(FrenchCardView, new FrenchCard(1, "hearts", true), "[ A HEARTS  ]"))
  testsFor(draw(FrenchCardView, new FrenchCard(11, "diamonds", true), "[ J DIAMONDS]"))
  testsFor(draw(FrenchCardView, new FrenchCard(12, "spades", true), "[ Q SPADES  ]"))
  testsFor(draw(FrenchCardView, new FrenchCard(13, "clubs", true), "[ K CLUBS   ]"))
}
