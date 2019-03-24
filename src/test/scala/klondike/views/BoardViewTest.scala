package klondike.views

import klondike.models.{Board, TableauPile}
import org.scalatest.FunSuite
import klondike.test_utils.TestModels._

class BoardViewTest extends FunSuite with BoardViewBehaviors {

  private val emptyBoardString =
    """[     ] [     ]         [     ] [     ] [     ] [     ]
      |
      |[     ] [     ] [     ] [     ] [     ] [     ] [     ]
      |
      |""".stripMargin

  testsFor(boardViewDraw("EmptyBoard", emptyBoard, SpanishCardView, emptyBoardString))


  private val initialBoard = SpanishBoardBuilder.build(7)
  private val knownCards = List(aceOfGolds, twoOfGolds, twoOfCups, twoOfClubs, knightOfCups, kingOfGolds, kingOfSwords)
  private val initialBoardWithKnownTableauPiles = new Board(
    initialBoard.deck,
    initialBoard.waste,
    initialBoard.foundations,
    initialBoard.tableauPiles.zip(knownCards).map { case (tp, card) => new TableauPile(card :: tp.cards.tail) }
  )
  private val initialBoardWithKnownTableauPilesString =
    """[XXXXX] [     ]         [     ] [     ] [     ] [     ]
      |
      |[ 1 GO] [XXXXX] [XXXXX] [XXXXX] [XXXXX] [XXXXX] [XXXXX]
      |        [ 2 GO] [XXXXX] [XXXXX] [XXXXX] [XXXXX] [XXXXX]
      |                [ 2 CU] [XXXXX] [XXXXX] [XXXXX] [XXXXX]
      |                        [ 2 CL] [XXXXX] [XXXXX] [XXXXX]
      |                                [11 CU] [XXXXX] [XXXXX]
      |                                        [12 GO] [XXXXX]
      |                                                [12 SW]
      |
      |""".stripMargin

  testsFor(boardViewDraw("InitialBoard", initialBoardWithKnownTableauPiles, SpanishCardView, initialBoardWithKnownTableauPilesString))
}
