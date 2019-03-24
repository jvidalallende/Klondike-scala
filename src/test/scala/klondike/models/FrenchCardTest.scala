package klondike.models

import klondike.exceptions.InvalidValueException
import org.scalatest.FunSuite

class FrenchCardTest extends FunSuite {

  test("givenACard_whenCheckingIfItIsEqualToItself_thenItIsTrue") {
    assert(new FrenchCard(3, "hearts") == new FrenchCard(3, "hearts"))
  }

  test("givenACardWithLimitsMinValue_whenCallingIsMin_thenItReturnsTrue") {
    assert(new FrenchCard(FrenchCard.MIN_VALUE, "hearts").isMin)
  }

  test("givenACardWithLimitsMaxValue_whenCallingIsMax_thenItReturnsTrue") {
    assert(new FrenchCard(FrenchCard.MAX_VALUE, "spades").isMax)
  }

  test("givenADownturnedCard_whenUpturningIt_thenItIsUpturned") {
    assert(new FrenchCard(3, "spades").upturn().upturned)
  }

  test("givenAUpturnedCard_whenDownturningIt_thenItIsNotUpturned") {
    assert(!new FrenchCard(3, "spades", true).downturn().upturned)
  }

  test("givenACard_whenCheckingIfItIsEqualToAString_thenItReturnsFalse") {
    assert(new FrenchCard(3, "spades") != "french-card")
  }

  test("givenAValueBelowMinValue_whenConstructionAFrenchCard_thenItThrowsAnException") {
    intercept[InvalidValueException] {
      new FrenchCard(FrenchCard.MIN_VALUE - 1, "spades")
    }
  }

  test("givenAValueAboveMaxValue_whenConstructionAFrenchCard_thenItThrowsAnException") {
    intercept[InvalidValueException] {
      new FrenchCard(FrenchCard.MAX_VALUE + 1, "spades")
    }
  }

  test("givenAnInvalidSuit_whenConstructingAFrenchCard_thenItThrowsAnException") {
    intercept[InvalidValueException] {
      new FrenchCard(FrenchCard.MIN_VALUE, "invalid-suit")
    }
  }
}
