package models

class SpanishCard(__value: Int, __suit: String, __upturned: Boolean = false)
  extends Card(SpanishCardLimits.filterValue(__value), SpanishCardLimits.filterSuit(__suit), __upturned) {

  override def upturn(): Card = new SpanishCard(value(), suit(), true)

  override def downturn(): Card = new SpanishCard(value(), suit(), false)

  override def isMin(): Boolean = {
    value == SpanishCardLimits.MIN_VALUE
  }

  override def isMax(): Boolean = {
    value == SpanishCardLimits.MAX_VALUE
  }
}
