package com.markgrand.geometry

import org.scalatest.FunSuite

class PointTest extends FunSuite {

  test("test$minus") {
    val p1 = Point(3,4)
    val p2 = Point(7,11)
    assert((p2-p1) == Magnitude2d(4,7))
  }

}
