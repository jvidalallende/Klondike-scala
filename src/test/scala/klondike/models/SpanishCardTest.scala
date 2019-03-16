package klondike.models

import klondike.exceptions.InvalidValueException
import org.scalatest.FunSuite

class SpanishCardTest extends FunSuite {

  test("givenACard_whenCheckingIfItIsEqualToItself_thenItIsTrue") {
    assert(new SpanishCard(3, "golds") == new SpanishCard(3, "golds"))
  }

  test("givenACardWithLimitsMinValue_whenCallingIsMin_thenItReturnsTrue") {
    assert(new SpanishCard(SpanishCard.MIN_VALUE, "golds").isMin)
  }

  test("givenACardWithLimitsMaxValue_whenCallingIsMax_thenItReturnsTrue") {
    assert(new SpanishCard(SpanishCard.MAX_VALUE, "clubs").isMax)
  }

  test("givenADownturnedCard_whenUpturningIt_thenItIsUpturned") {
    assert(new SpanishCard(3, "clubs").upturn().upturned)
  }

  test("givenAUpturnedCard_whenDownturningIt_thenItIsNotUpturned") {
    assert(!new SpanishCard(3, "clubs", true).downturn().upturned)
  }

  test("givenACard_whenCheckingIfItIsEqualToAString_thenItReturnsFalse") {
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
