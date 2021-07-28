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
        assert(NoIntersection() == v1.intersection(EuclideanVector(Point(4, 4), Point(6, 6))))
        assert(NoIntersection() == v2.intersection(EuclideanVector(Point(-4, -4), Point(-6, -6))))
      }
      "If they do overlap, then the intersection should be an overlap" in {
        assert(OverlappingVectors() == v1.intersection(EuclideanVector(Point(2, 2), Point(4, 4))))
        assert(OverlappingVectors() == v2.intersection(EuclideanVector(Point(-2, -2), Point(-4, -4))))
      }
    }
  }

}
