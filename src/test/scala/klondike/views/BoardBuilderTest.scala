package klondike.views

import org.scalatest.FunSuite

class BoardBuilderTest extends FunSuite with BoardBuilderBehaviors {

  testsFor(checkBuild("SpanishBoardBuilder", SpanishBoardBuilder.build))

}
