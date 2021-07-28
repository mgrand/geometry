package com.markgrand.geometry

import org.scalatest.freespec.AnyFreeSpec

class EuclideanVectorTest extends AnyFreeSpec {
  private val v1 = EuclideanVector(Point(1, 1), Point(3, 3))
  private val v2 = EuclideanVector(Point(-1, -1), Point(-3, -3))
  private val v3 = EuclideanVector(Point(1, -1), Point(-1, 1))
  private val v4 = EuclideanVector(Point(-1, 1), Point(1, -1))

  "isDegenerate" - {
    val A = Point(3, 4)
    val B = Point(6, 8)
    val lineSegment = EuclideanVector(A, B)
    val degenerate = EuclideanVector(A, A)
    "When given a degenerate vector should return true" in {
      assert(degenerate.isDegenerate)
    }
    "When given a non-degenerate vector should return false" in {
      assert(!lineSegment.isDegenerate)
    }
  }

  "intersection" - {
    "Given two collinear vectors" - {
      "if they don't overlap, then there is no intersection" in {
        assertResult(NoIntersection()) {
          v1.intersection(EuclideanVector(Point(4, 4), Point(6, 6)))
        }
        assertResult(NoIntersection()) {
          v2.intersection(EuclideanVector(Point(-4, -4), Point(-6, -6)))
        }
      }
      "If they do overlap, then the intersection should be an overlap" in {
        assertResult(OverlappingVectors()) {
          v1.intersection(EuclideanVector(Point(2, 2), Point(4, 4)))
        }
        assertResult(OverlappingVectors()) {
          v2.intersection(EuclideanVector(Point(-2, -2), Point(-4, -4)))
        }
      }
    }
    "Given two parallel vectors, they should be recognized as parallel" in {
      assertResult(Parallel()) {
        v1.intersection(EuclideanVector(Point(1, 2), Point(3, 4)))
        v2.intersection(EuclideanVector(Point(-1, -2), Point(-3, -4)))
      }
    }
    "Given two non-parallel vectors" - {
      "If they do not intersect then recognize it" in {
        assertResult(NoIntersection()) {
          v1.intersection(v2)
        }
        assertResult(NoIntersection) {
          v2.intersection(v1)
        }
        assertResult(NoIntersection()) {
          v1.intersection(EuclideanVector(Point(7, 13), Point(23, 11)))
        }
        assertResult(NoIntersection()) {
          v2.intersection(EuclideanVector(Point(7, 13), Point(23, 11)))
        }
      }
      "If they do intersect, the intersection point should be returned" in {
        assertResult(IntersectionPoint(Point(2.0, 2.0))) {
          v1.intersection(EuclideanVector(Point(1, 3), Point(3, 1)))
        }
        assertResult(IntersectionPoint(Point(-2.0, -2.0))) {
          v2.intersection(EuclideanVector(Point(-1, -3), Point(-3, -1)))
        }
      }
    }
  }

  "slope" - {
    "Vectors with a non-zero x delta should return the expected numeric value" in {
      assertResult(1.0) {
        v1.slope
      }
      assertResult(1.0) {
        v2.slope
      }
      assertResult(-1.0) {
        v3.slope
      }
      assertResult(-1.0) {
        v4.slope
      }
    }
    "for a vector with a zero x delta and a positive y delta the result should be positive infinity" in {
      val yp = EuclideanVector(Point(1, 1), Point(1, 8))
      assertResult(Double.PositiveInfinity) {
        yp.slope
      }
    }
    "for a vector with a zero x delta and a negative y delta the result should be negative infinity" in {
      val yp = EuclideanVector(Point(1, 8), Point(1, 1))
      assertResult(Double.NegativeInfinity) {
        yp.slope
      }
    }
    "for a vector with a zero x delta and a zero y delta the result should be zero" in {
      val yp = EuclideanVector(Point(1, 1), Point(1, 1))
      assertResult(0.0) {
        yp.slope
      }
    }
  }

  "magnitude" in {
    assertResult(java.lang.Math.sqrt(8.0)) {
      v1.magnitude
    }
    assertResult(java.lang.Math.sqrt(8.0)) {
      v2.magnitude
    }
    assertResult(java.lang.Math.sqrt(8.0)) {
      v3.magnitude
    }
    assertResult(java.lang.Math.sqrt(8.0)) {
      v4.magnitude
    }
  }
}
