package com.markgrand.geometry

import org.scalatest.freespec.AnyFreeSpec

class Rectangle2DTest extends AnyFreeSpec {
  private val v1 = Rectangle2D(Point(-2, 2), Point(2, 2), Point(2, -2), Point(-2,-2))
  private val v2 = Rectangle2D(Point(3, 0), Point(6, 3), Point(3, 6), Point(0,3))
  private val v20 = Rectangle2D(Point(0, 20), Point(10, 20), Point(10, 10), Point(0, 10))

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

  "intersectionPoints" - {
    "Rectangles that do not intersect should produce an empty set of intersection points" in {
      assertResult(Set.empty[Point]) {
        v1 intersectionPoints v20
      }
    }
    "Rectangles that do intersect should produce a correct set of intersection points" in {
      assertResult(Set(Point(1,2), Point(2, 1))) {
        v1 intersectionPoints v2
      }
    }
  }

  "contains Point" - {
    "A point that is contained" in {
      assert(v1.contains(Point(0,0)))
      assert(v2.contains(Point(3,1)))
    }
    "A point that is not contained" in {
      assert(!v1.contains(Point(4,3)))
      assert(!v2.contains(Point(-1,-1)))
    }
    "A corner point is not contained" in {
      assert(!v1.contains(Point(2,2)))
      assert(!v2.contains(Point(3,6)))
    }
    "An edge point is not contained" in {
      assert(!v1.contains(Point(0, 2)))
      assert(!v2.contains(Point(4, 1)))
    }
  }

  "Contains Rectangle" - {
    "A rectangle that is contained" in {
      assert(v1.contains(Rectangle2D(Point(-1, 1), Point(1, 1), Point(1, -1), Point(-1, -1))))
      assert(v2.contains(Rectangle2D(Point(2.5, 3.5), Point(3.5, 3.5), Point(3.5, 2.5), Point(2.5, 2.5))))
    }
    "A rectangle outside of is not contained" in {
      assert(!v1.contains(Rectangle2D(Point(10, 10), Point(11, 10), Point(11, 9), Point(10, 9))))
      assert(!v2.contains(Rectangle2D(Point(10, 10), Point(11, 10), Point(11, 9), Point(10, 9))))
    }
    "An intersecting rectangle is not contained" in {
      assert(!v1.contains(Rectangle2D(Point(-5, 1), Point(5, 1), Point(5, 0), Point(-5, 0))))
      assert(!v2.contains(Rectangle2D(Point(-5, 1), Point(5, 1), Point(5, 0), Point(-5, 0))))
    }
  }
}
