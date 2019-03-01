import models.{SpanishCard, SpanishCardLimits}
import org.scalatest.FunSuite

class SpanishCardTest extends FunSuite {

  test("givenOneCard_whenCheckingIfItIsEqualToItself_ItIsTrue") {
    assert(new SpanishCard(3, "golds") == new SpanishCard(3, "golds"))
  }

  test("givenOneCardWithLimitsMinValue_whenCallingIsMin_thenItReturnsTrue") {
    assert(new SpanishCard(SpanishCardLimits.MIN_VALUE, "golds").isMin())
  }

  test("givenOneCardWithLimitsMaxValue_whenCallingIsMax_thenItReturnsTrue") {
    assert(new SpanishCard(SpanishCardLimits.MAX_VALUE, "clubs").isMax())
  }

  test("givenOneDownturnedCard_whenUpturningIt_thenItIsUpturned") {
    assert(new SpanishCard(3, "clubs").upturn().upturned())
  }

  test("givenOneUpturnedCard_whenDownturningIt_thenItIsNotUpturned") {
    assert(!new SpanishCard(3, "clubs", true).downturn().upturned())
  }
}
