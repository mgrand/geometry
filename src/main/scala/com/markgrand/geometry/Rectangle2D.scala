package com.markgrand.geometry

/**
 * Represent a 2 dimensional rectangle whose sides may or may not be parallel or perpendicular to the x or y axis. These
 * rectangles are defined by four vectors that represent the edges.
 * <p>
 * The four corner points must be distinct and define a quadrilateral whose opposite sides are of equal
 *              length and whose corners are right angles.
 *              @param a A corner point
 */
class Rectangle2D(a: Point, b: Point, c: Point, d: Point) {
  private val s1 = EuclideanVector(a, b)
  private val s2 = EuclideanVector(b, c)
  private val s3 = EuclideanVector(c, d)
  private val s4 = EuclideanVector(d, a)

  ensureValidRectangle()

  /** return true if the two vectors are 90 degrees apart */
  private def isRightAngle(s1: EuclideanVector, s2: EuclideanVector): Boolean = {
    val slope1 = s1.slope
    val slope2 = s2.slope
    if (slope1 == 0.0)
      slope2 == Double.PositiveInfinity || slope2 == Double.NegativeInfinity
    else if (slope1 == Double.PositiveInfinity || slope1 == Double.NegativeInfinity)
      slope1 == 0
    else
      Math.isEqual((slope1 * slope2).abs, 1.0)
  }

  private def ensureValidRectangle(): Unit = {
    require(Math.isEqual(s1.magnitude, s3.magnitude) && Math.isEqual(s2.magnitude, s4.magnitude),
      "lengths of alternate sides must be equal")
    require(isRightAngle(s1, s2), "The sides of the rectangle must form right angles.")
  }
}

object Rectangle2D {
  def apply(a: Point, b: Point, c: Point, d: Point): Rectangle2D = new Rectangle2D(a, b, c, d)
}
