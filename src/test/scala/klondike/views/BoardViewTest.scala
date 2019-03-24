package klondike.views

import klondike.models.{Board, TableauPile}
import org.scalatest.FunSuite
import klondike.test_utils.TestModels._

class BoardViewTest extends FunSuite with BoardViewBehaviors {

  private val emptyBoardString =
    """[         ] [         ]             [         ] [         ] [         ] [         ]
      |
      |[         ] [         ] [         ] [         ] [         ] [         ] [         ]
      |
      |""".stripMargin

  testsFor(boardViewDraw("EmptyBoard", emptyBoard, SpanishCardView, emptyBoardString))


  private val initialBoard = SpanishBoardBuilder.build(7)
  private val knownCards = List(aceOfGolds, twoOfGolds, twoOfCups, twoOfClubsSpanish, knightOfCups, kingOfGolds, kingOfSwords)
  private val initialBoardWithKnownTableauPiles = new Board(
    initialBoard.deck,
    initialBoard.waste,
    initialBoard.foundations,
    initialBoard.tableauPiles.zip(knownCards).map { case (tp, card) => new TableauPile(card :: tp.cards.tail) }
  )
  private val initialBoardWithKnownTableauPilesString =
    """[XXXXXXXXX] [         ]             [         ] [         ] [         ] [         ]
      |
      |[ 1 GOLDS ] [XXXXXXXXX] [XXXXXXXXX] [XXXXXXXXX] [XXXXXXXXX] [XXXXXXXXX] [XXXXXXXXX]
      |            [ 2 GOLDS ] [XXXXXXXXX] [XXXXXXXXX] [XXXXXXXXX] [XXXXXXXXX] [XXXXXXXXX]
      |                        [ 2 CUPS  ] [XXXXXXXXX] [XXXXXXXXX] [XXXXXXXXX] [XXXXXXXXX]
      |                                    [ 2 CLUBS ] [XXXXXXXXX] [XXXXXXXXX] [XXXXXXXXX]
      |                                                [11 CUPS  ] [XXXXXXXXX] [XXXXXXXXX]
      |                                                            [12 GOLDS ] [XXXXXXXXX]
      |                                                                        [12 SWORDS]
      |
      |""".stripMargin

  testsFor(boardViewDraw("InitialBoard", initialBoardWithKnownTableauPiles, SpanishCardView, initialBoardWithKnownTableauPilesString))
}
