package com.markgrand.geometry

import org.scalatest.FunSuite

class PointTest extends FunSuite {
  private val p1 = Point(3,4)
  private val p2 = Point(7,11)

  test("test$minus") {
    assert((p2-p1) == Magnitude2d(4,7))
  }

  test( "cross") {
    assertResult(0.0d) {
      p1 cross p1
    }
    assertResult(3.0d*11 - 7*4) {
      p1 cross p2
    }
    assertResult(7.0d*4 - 3*11) {
      p2 cross p1
    }
  }

}
