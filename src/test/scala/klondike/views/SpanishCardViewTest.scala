package klondike.views

import klondike.models.SpanishCard
import org.scalatest.FunSuite

class SpanishCardViewTest extends FunSuite with CardViewBehaviors {

  // Whitespace
  testsFor(drawWhitespace("Spanish", SpanishCardView, "           "))

  // Placeholder
  testsFor(drawPlaceholder("Spanish", SpanishCardView, "[         ]"))

  // Downturned card
  testsFor(draw(SpanishCardView, new SpanishCard(10, "golds"), "[XXXXXXXXX]"))

  // Single-digit values
  testsFor(draw(SpanishCardView, new SpanishCard(1, "golds", true), "[ 1 GOLDS ]"))
  testsFor(draw(SpanishCardView, new SpanishCard(2, "cups", true), "[ 2 CUPS  ]"))
  testsFor(draw(SpanishCardView, new SpanishCard(6, "swords", true), "[ 6 SWORDS]"))
  testsFor(draw(SpanishCardView, new SpanishCard(7, "clubs", true), "[ 7 CLUBS ]"))

  // Double-digit values
  testsFor(draw(SpanishCardView, new SpanishCard(8, "golds", true), "[10 GOLDS ]"))
  testsFor(draw(SpanishCardView, new SpanishCard(9, "cups", true), "[11 CUPS  ]"))
  testsFor(draw(SpanishCardView, new SpanishCard(10, "swords", true), "[12 SWORDS]"))
  testsFor(draw(SpanishCardView, new SpanishCard(10, "clubs", true), "[12 CLUBS ]"))
}
