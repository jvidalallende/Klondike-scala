package klondike.models

import klondike.exceptions.EmptyPileException

abstract class Pile(__cards: List[Card], __name: String) {

  private val _cards = __cards

  val name: String = __name

  def cards: List[Card] = _cards

  def pick(): (Card, Pile)

  def put(card: Card): Pile

  def empty: Boolean = {
    _cards match {
      case Nil => true
      case _ => false
    }
  }

  override def equals(that: Any): Boolean =
    that match {
      case that: Pile => this.cards == that.cards
      case _ => false
    }

  // Convenience method place here so that child classes can reuse it
  protected def assertNotEmpty(): Unit = {
    _cards match {
      case Nil => throw EmptyPileException(s"$name is empty")
      case _ =>
    }
  }
}
