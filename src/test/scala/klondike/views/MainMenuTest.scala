package klondike.views

import klondike.models.Board
import klondike.test_utils.TestModels._
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite
import org.scalatest.time.SpanSugar._
import org.scalatest.concurrent.Timeouts._

class MainMenuTest extends FunSuite with MainMenuBehaviors with MockFactory {

  testsFor(mainMenuTests(SpanishGameFactory))
  testsFor(finishGame(SpanishGameFactory, List(kingOfGolds, knightOfCups, kingOfSwords, kingOfClubsSpanish).map(c => foundationWithCard(c)), kingOfCups))

  testsFor(mainMenuTests(FrenchGameFactory))
  testsFor(finishGame(FrenchGameFactory, List(kingOfHearts, knightOfDiamonds, kingOfSpades, kingOfClubsFrench).map(c => foundationWithCard(c)), kingOfDiamonds))

}
