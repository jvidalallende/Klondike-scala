package klondike.models

import klondike.exceptions.EmptyPileException

/* A TableauPile is a Pile with some cards its cards downturned, and some others upturned.
   Only upturned cards can be picked.
   If a pick leaves only downturned cards in the pile, the top card will be upturned */

class TableauPile(__cards: List[Card], __name: String = "TableauPile") extends Pile[TableauPile] {

  override val name: String = __name
  override val cards: List[Card] = __cards

  def this(pile: Pile[TableauPile]) = this(pile.cards, pile.name)

  override def pick(): (Card, TableauPile) = {
    cards match {
      case Nil => throw EmptyPileException(s"$name is empty")
      case _ => val newPile = {
        cards.tail match {
          case Nil => Nil
          case head :: tail => head.upturn() :: tail
        }
      }
        (cards.head, new TableauPile(newPile))
    }
  }

  override def put(card: Card): TableauPile = new TableauPile(card.upturn() :: cards)

  override def equals(that: Any): Boolean = {
    that match {
      case that: TableauPile => this.cards == that.cards
      case _ => false
    }
  }

  // Tableau piles also support pick/put with multiple cards

  def pick(numberOfCards: Int): (List[Card], TableauPile) = {
    def pick(picked: List[Card], remaining: List[Card], numberOfCards: Int): (List[Card], List[Card]) = {
      numberOfCards match {
        case 0 => (picked, remaining)
        case _ if remaining.isEmpty => throw EmptyPileException("Pile is empty")
        case _ if !remaining.head.upturned => throw EmptyPileException("Not enough upturned cards to pick")
        case _ => pick(remaining.head :: picked, remaining.tail, numberOfCards - 1)
      }
    }

    numberOfCards match {
      case 0 => (Nil, new TableauPile(cards))
      case _ =>
        val (picked, remaining) = pick(Nil, cards, numberOfCards)
        val newTableauPile = remaining match {
          case Nil => new TableauPile(Nil)
          case _ => new TableauPile(remaining.head.upturn() :: remaining.tail)
        }
        (picked, newTableauPile)
    }
  }

  def put(cards: List[Card]): TableauPile = {
    cards match {
      case Nil => new TableauPile(this.cards)
      case head :: tail => new TableauPile(head :: this.cards).put(tail)
    }
  }
}
