package models

abstract class Card(__value: Int, __suit: String, __upturned: Boolean = false) {

  private val _value = __value
  private val _suit = __suit
  private val _upturned = __upturned

  def value(): Int = _value

  def suit(): String = _suit

  def upturned(): Boolean = _upturned

  override def equals(that: Any): Boolean =
    that match {
      case that: Card =>
        this.value == that.value && this.suit == that.suit && this.upturned == that.upturned
      case _ => false
    }

  def upturn(): Card

  def downturn(): Card

  def isMin(): Boolean

  def isMax(): Boolean
}
