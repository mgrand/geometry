package com.markgrand.geometry

import org.scalatest.FunSuite
import org.scalatest.freespec.AnyFreeSpec

class EuclideanVectorTest extends AnyFreeSpec {
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
    val v1 = EuclideanVector(Point(1, 1), Point(3, 3))
    val v2 = EuclideanVector(Point(-1, -1), Point(-3, -3))
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
      }
    }
  }

}
