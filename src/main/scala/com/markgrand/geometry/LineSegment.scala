package com.markgrand.geometry

/**
 * Represent a line segment as two points
 */
case class LineSegment(a: Point, b: Point) {
  /**
   * @return true if this line segment's endpoints are the same point.
   */
  def isDegenerate: Boolean = {a == b}
}
