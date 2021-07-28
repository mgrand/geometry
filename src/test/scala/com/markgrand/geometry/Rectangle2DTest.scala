package com.markgrand.geometry

import org.scalatest.freespec.AnyFreeSpec

class Rectangle2DTest extends AnyFreeSpec {

  "Create a rectangle" - {
    "with valid corner points" in {
      assert(null != Rectangle2D(Point(-2, 2), Point(2, 2), Point(2, -2), Point(-2,-2)))
      assert(null != Rectangle2D(Point(3, 0), Point(6, 3), Point(3, 6), Point(0,3)))
    }
  }
}
