package com.markgrand.geometry

import org.scalatest.FunSuite

class LineSegmentTest extends FunSuite {
  val A = new Point(3,4)
  val B = new Point(6,8)

  test("testB") {
    val lineSegment = LineSegment(A,B)
    assert(lineSegment.b == B)
  }

  test("testA") {
    val lineSegment = LineSegment(A,B)
    assert(lineSegment.a == A)
  }

  test("testIsDegenerate") {
    val lineSegment = LineSegment(A,B)
    val degenerate = LineSegment(A,A)
    assert(degenerate.isDegenerate)
    assert(!lineSegment.isDegenerate)
  }

}
