package klondike.utils

import org.scalatest.FunSuite

class ListHelpersTest extends FunSuite with ListHelpersBehaviors {

  private val oneToFive = 1 :: 2 :: 3 :: 4 :: 5 :: Nil

  testsFor(replacementOutOfBounds(Nil, 0, 7, "givenAnEmptyList_whenReplacingAtZero_thenExceptionIsThrown"))
  testsFor(replacementOutOfBounds(Nil, 7, 7, "givenAnEmptyList_whenReplacingIndexBiggerThanZero_thenExceptionIsThrown"))
  testsFor(replacementOutOfBounds(oneToFive, -1, 7, "givenAListWithFiveElements_whenReplacingAtMinusOne_thenExceptionIsThrown"))
  testsFor(replacementOutOfBounds(oneToFive, 5, 7, "givenAListWithFiveElements_whenReplacingAtIndexFive_thenExceptionIsThrown"))

  testsFor(replacementCanBeDone(oneToFive, 0, 7, 7 :: oneToFive.tail,
    "givenAListWithFiveElements_whenReplacingAtIndexZero_thenResultContainsTheReplacementAtHead"))

  testsFor(replacementCanBeDone(oneToFive, 3, 7, 1 :: 2 :: 3 :: 7 :: 5 :: Nil,
    "givenAListWithFiveElements_whenReplacingInTheMiddle_thenResultContainsTheReplacementInTheExpectedPosition"))

  testsFor(replacementCanBeDone(oneToFive, 4, 7, 1 :: 2 :: 3 :: 4 :: 7 :: Nil,
    "givenAListWithFiveElements_whenReplacingLastIndex_thenResultContainsTheReplacementInTheLastPosition"))
}
