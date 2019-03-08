package klondike.views

import klondike.models.SpanishCard
import org.scalatest.FunSuite

class SpanishCardViewTest extends FunSuite with SpanishCardViewBehaviors {

  // Downturned card
  testsFor(checkView(new SpanishCard(1, "golds"), "[XXXXX]"))

  // Single-digit values
  testsFor(checkView(new SpanishCard(1, "golds", true), "[ 1 GO]"))
  testsFor(checkView(new SpanishCard(2, "cups", true), "[ 2 CU]"))
  testsFor(checkView(new SpanishCard(6, "swords", true), "[ 6 SW]"))
  testsFor(checkView(new SpanishCard(7, "clubs", true), "[ 7 CL]"))

  // Double-digit values
  testsFor(checkView(new SpanishCard(8, "golds", true), "[10 GO]"))
  testsFor(checkView(new SpanishCard(9, "cups", true), "[11 CU]"))
  testsFor(checkView(new SpanishCard(10, "swords", true), "[12 SW]"))
  testsFor(checkView(new SpanishCard(10, "clubs", true), "[12 CL]"))
}
