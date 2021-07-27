package com.markgrand.geometry

/**
 * Represent a line segment as two points.
 *
 * @author Mark Grand
 */
case class LineSegment(a: Point, b: Point) {
  /**
   * @return true if this line segment's endpoints are the same point.
   */
  def isDegenerate: Boolean = {a == b}

  /**
   * Compute the intersection between two line segments. This uses the algorithm documented in
   * [[https://stackoverflow.com/questions/563198/how-do-you-detect-where-two-line-segments-intersect]].
   * @param that the other line segment
   * @return NoIntersection, LineSegmentsIntersection or OverlappingLineSegment
   */
  def intersectionPoint(that: LineSegment): LineSegmentsIntersection = NoIntersection()
}
