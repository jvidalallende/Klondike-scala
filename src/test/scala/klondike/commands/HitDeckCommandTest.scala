package klondike.commands

import klondike.controllers.MovementFactory
import klondike.exceptions.EmptyPileException
import klondike.models._
import klondike.views.SpanishGameFactory
import org.scalatest.FunSuite

class HitDeckCommandTest extends FunSuite {

  private val aceOfGolds = new SpanishCard(1, "golds")

  private val movementFactory = new MovementFactory(SpanishGameFactory.tableauPileValidator)
  private val command = new HitDeckCommand("", movementFactory)

  test("givenAGameWithEmptyDeck_whenDoingHitDeckMovement_thenExceptionIsRaised") {
    val game = new Game(new Board(new Deck(Nil), new Waste(Nil), Nil, Nil))
    intercept[EmptyPileException] {
      command.execute(game)
    }
  }

  test("givenAGameWithAOneCardDeck_whenDoingHitDeckMovement_thenTheNewGameHasThatCardInTheWaste") {
    val game = new Game(new Board(new Deck(aceOfGolds :: Nil), new Waste(Nil), Nil, Nil))
    val expected = new Game(new Board(new Deck(Nil), new Waste(aceOfGolds.upturn() :: Nil), Nil, Nil))
    assert(expected == command.execute(game))
  }
}
