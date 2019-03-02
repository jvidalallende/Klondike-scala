package models

import exceptions.EmptyPileException

abstract class Pile(__cards: List[Card]) {

  private val _cards = __cards

  def cards(): List[Card] = _cards

  def pick(): (Card, Pile)

  def put(card: Card): Pile

  def empty(): Boolean = {
    _cards match {
      case Nil => true
      case _ => false
    }
  }

  def assertNotEmpty(): Unit = {
    _cards match {
      case Nil => throw EmptyPileException("Pile is empty")
      case _ =>
    }
  }

  override def equals(that: Any): Boolean =
    that match {
      case that: Pile => this.cards == that.cards
      case _ => false
    }
}
