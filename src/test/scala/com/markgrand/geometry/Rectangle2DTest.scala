package com.markgrand.geometry

import org.scalatest.freespec.AnyFreeSpec

class Rectangle2DTest extends AnyFreeSpec {

  "Create a rectangle" - {
    "with valid corner points" in {
      assert(null != Rectangle2D(Point(-2, 2), Point(2, 2), Point(2, -2), Point(-2,-2)))
      assert(null != Rectangle2D(Point(3, 0), Point(6, 3), Point(3, 6), Point(0,3)))
    }
    "with unequal alternate side lengths" in {
      assertThrows[IllegalArgumentException](Rectangle2D(Point(-2, 2), Point(2, 2), Point(2, -3), Point(-2,-2)))
      assertThrows[IllegalArgumentException](Rectangle2D(Point(-2, 2), Point(2, 2), Point(2, -2), Point(-2,-3)))
      assertThrows[IllegalArgumentException](Rectangle2D(Point(3, 0), Point(6, 3), Point(3, 7), Point(0,4)))
    }
    "parallelogram" in {
      assertThrows[IllegalArgumentException](null != Rectangle2D(Point(-2, 2), Point(2, 2), Point(3, -2), Point(-1,-2)))
    }
  }
}
