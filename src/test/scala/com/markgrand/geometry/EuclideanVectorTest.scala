package com.markgrand.geometry

import org.scalatest.FunSuite

class EuclideanVectorTest extends FunSuite {
  private val A = Point(3,4)
  private val B = Point(6,8)

  test("testB") {
    val lineSegment = EuclideanVector(A,B)
    assert(lineSegment.b == B)
  }

  test("testA") {
    val lineSegment = EuclideanVector(A,B)
    assert(lineSegment.a == A)
  }

  test("testIsDegenerate") {
    val lineSegment = EuclideanVector(A,B)
    val degenerate = EuclideanVector(A,A)
    assert(degenerate.isDegenerate)
    assert(!lineSegment.isDegenerate)
  }

}
