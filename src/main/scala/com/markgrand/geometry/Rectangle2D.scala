package com.markgrand.geometry

/**
 * Represent a 2 dimensional rectangle whose sides may or may not be parallel or perpendicular to the x or y axis. These
 * rectangles are defined by four vectors that represent the edges.
 * <p>
 * The four corner points must be distinct and define a quadrilateral whose opposite sides are of equal
 * length and whose corners are right angles.
 *
 * @param a A corner point
 * @author Mark Grand
 */
class Rectangle2D(val a: Point, val b: Point, val c: Point, val d: Point) {
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
    else // If the two vectors are at right angles, slope1 == 1/slope2
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
   * @return the set of intersection points. This will be empty if there are none.
   */
  def intersectionPoints(that: Rectangle2D): Set[Point] = {
    val builder = Set.newBuilder[Point]

    def addIntersection(v1: EuclideanVector, v2: EuclideanVector): Unit = {
      v1 intersection v2 match {
        case p: IntersectionPoint => builder += p.point
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

  /**
   * Return true if this rectangle contains the given point.
   *
   * @param p the point to check
   * @return True if the point is contained in this rectangle; otherwise false.
   */
  def contains(p: Point): Boolean = {
    def vectorIntersectsEdge(v: EuclideanVector, edge: EuclideanVector, corner: Point): Boolean = {
      v intersection edge match {
        case p: IntersectionPoint => p.point != corner
        case NoIntersection => false
        case _: OverlappingVectors => true
      }
    }

    def vectorIntersectAnyEdge(v: EuclideanVector, corner: Point): Boolean = {
      val intersectsS1 = vectorIntersectsEdge(v, s1, corner)
      val intersectsS2 = vectorIntersectsEdge(v, s2, corner)
      val intersectsS3 = vectorIntersectsEdge(v, s3, corner)
      val intersectsS4 = vectorIntersectsEdge(v, s4, corner)
      intersectsS1 || intersectsS2 || intersectsS3 || intersectsS4
    }
    // If none of the lines from the given point to the four corners of the rectangle intersect an edge of the
    // rectangle, then the point is contained in the rectangle
    !vectorIntersectAnyEdge(EuclideanVector(p, a), a) && !vectorIntersectAnyEdge(EuclideanVector(p, b), b) &&
      !vectorIntersectAnyEdge(EuclideanVector(p, c), c) && !vectorIntersectAnyEdge(EuclideanVector(p, d), d)
  }

  /**
   * Determine if this rectangle contains the given rectangle
   *
   * @param r The rectangle that may be contained by this rectangle
   * @return true if this rectangle contains the given rectangle; otherwise false.
   */
  def contains(r: Rectangle2D): Boolean = {
    // if an arbitrary corner of the given rectangle is contained by this rectangle and the two rectangles do not
    // intersect, then this rectangle contains the given rectangle.
    contains(r.a) && intersectionPoints(r).isEmpty
  }

  /**
   * Determine if this rectangle is adjacent to the given rectangle
   *
   * @param r The rectangle that may be adjacent to this rectangle
   * @return True if the given rectangle is adjacent to this one; otherwise false.
   */
  def adjacent(r: Rectangle2D): Boolean = {
    def sideOverlapsWithOtherRectangle(s: EuclideanVector): Boolean = {
      s.overlaps(r.s1) || s.overlaps(r.s2) || s.overlaps(r.s3) || s.overlaps(r.s4)
    }
    sideOverlapsWithOtherRectangle(s1) || sideOverlapsWithOtherRectangle(s2) || sideOverlapsWithOtherRectangle(s3) || sideOverlapsWithOtherRectangle(s4)
  }
}

object Rectangle2D {
  def apply(a: Point, b: Point, c: Point, d: Point): Rectangle2D = new Rectangle2D(a, b, c, d)
}
