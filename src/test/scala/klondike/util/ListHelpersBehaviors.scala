package klondike.util

import klondike.exceptions.InvalidValueException
import org.scalatest.FunSuite

trait ListHelpersBehaviors {
  this: FunSuite =>

  def replacementCanBeDone[A](original: List[A], index: Int, replacement: A, expected: List[A], testName: String) {
    test(testName) {
      assert(expected == ListHelpers.replaceAt(original, index, replacement))
    }
  }

  def replacementOutOfBounds[A](original: List[A], index: Int, replacement: A, testName: String) {
    test(testName) {
      intercept[InvalidValueException] {
        ListHelpers.replaceAt(original, index, replacement)
      }
    }
  }
}