package klondike.models

import klondike.exceptions.InvalidValueException
import org.scalatest.FunSuite

class SpanishCardTest extends FunSuite {

  test("givenOneCard_whenCheckingIfItIsEqualToItself_thenItIsTrue") {
    assert(new SpanishCard(3, "golds") == new SpanishCard(3, "golds"))
  }

  test("givenOneCardWithLimitsMinValue_whenCallingIsMin_thenItReturnsTrue") {
    assert(new SpanishCard(SpanishCard.MIN_VALUE, "golds").isMin)
  }

  test("givenOneCardWithLimitsMaxValue_whenCallingIsMax_thenItReturnsTrue") {
    assert(new SpanishCard(SpanishCard.MAX_VALUE, "clubs").isMax)
  }

  test("givenOneDownturnedCard_whenUpturningIt_thenItIsUpturned") {
    assert(new SpanishCard(3, "clubs").upturn().upturned)
  }

  test("givenOneUpturnedCard_whenDownturningIt_thenItIsNotUpturned") {
    assert(!new SpanishCard(3, "clubs", true).downturn().upturned)
  }

  test("givenOneCard_whenCheckingIfItIsEqualToAString_thenItReturnsFalse") {
    assert(new SpanishCard(3, "clubs") != "spanish-card")
  }

  test("givenAValueBelowMinValue_whenConstructionASpanishCard_thenItThrowsAnException") {
    intercept[InvalidValueException] {
      new SpanishCard(SpanishCard.MIN_VALUE - 1, "clubs")
    }
  }

  test("givenAValueAboveMaxValue_whenConstructionASpanishCard_thenItThrowsAnException") {
    intercept[InvalidValueException] {
      new SpanishCard(SpanishCard.MAX_VALUE + 1, "clubs")
    }
  }

  test("givenAnInvalidSuit_whenConstructingASpanishCard_thenItThrowsAnException") {
    intercept[InvalidValueException] {
      new SpanishCard(SpanishCard.MIN_VALUE, "invalid-suit")
    }
  }
}
