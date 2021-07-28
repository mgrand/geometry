package com.markgrand.geometry

import scala.collection.mutable

/**
 * Represent a 2 dimensional rectangle whose sides may or may not be parallel or perpendicular to the x or y axis. These
 * rectangles are defined by four vectors that represent the edges.
 * <p>
 * The four corner points must be distinct and define a quadrilateral whose opposite sides are of equal
 *              length and whose corners are right angles.
 *
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

  /**
   * Return a set of points where the edges of this and the given rectangle intersect. Overlapping edges are not
   * considered to be intersecting.
   *
   * @param that The other rectangle
   * @return the set of intersction points. This will be empty if there are none.
   */
  def intersectionPoints(that: Rectangle2D): Set[Point] = {
    val builder = Set.newBuilder[Point]
    def addIntersection(v1: EuclideanVector, v2: EuclideanVector): Unit = {
      v1 intersection v2 match {
        case p :IntersectionPoint => builder += p
        case _ =>
      }
    }
    def addIntersections(v: EuclideanVector): Unit = {
      addIntersection(v, that.s1)
      addIntersection(v, that.s2)
      addIntersection(v, that.s3)
      addIntersection(v, that.s4)
    }
    addIntersections(s1)
    addIntersections(s2)
    addIntersections(s3)
    addIntersections(s4)
    builder.result()
  }
}

object Rectangle2D {
  def apply(a: Point, b: Point, c: Point, d: Point): Rectangle2D = new Rectangle2D(a, b, c, d)
}
