package com.markgrand.geometry

import org.scalatest.FunSuite

class Magnitude2dTest extends FunSuite {

  test("testTimes") {
    val mag = Magnitude2d(3,4)
    assertResult(Magnitude2d(6,8)) {
      mag times 2.0
    }
  }

}
