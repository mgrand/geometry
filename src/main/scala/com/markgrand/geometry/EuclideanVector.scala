package com.markgrand.geometry

/**
 * Represent a Euclidean vector as two points.
 *
 * @author Mark Grand
 */
case class EuclideanVector(a: Point, b: Point) {
  /**
   * @return true if the endpoints are the same point.
   */
  def isDegenerate: Boolean = {a == b}

  /**
   * Compute the intersection between two line segments. This uses the algorithm documented in
   * [[https://stackoverflow.com/questions/563198/how-do-you-detect-where-two-line-segments-intersect]].
   * @param that the other line segment
   * @return [[IntersectionPoint]], [[NoIntersection]], [[Parallel]] (a subclass of NoIntersection), or
   *         [[OverlappingVectors]].
   */
  def intersection(that: EuclideanVector): VectorIntersection = {
    val p = a
    val q = that.a
    val r = b - p
    val s = that.b - q
    val rCrossS = r cross s
    val qMinusPCrossR = (q - p) cross r

    // Handle the case of collinear vectors
    def collinear() = {
      val t0 = ((q - p) dot r) / (r dot r)
      val t1 = t0 + (s dot r) / (r dot r)
      val tMin = t0 min t1
      val tMax = t0 max t1
      if (tMin <= 1.0 && tMax >= 0)
        OverlappingVectors()
      else
        NoIntersection()
    }

    if (Math.isZero(rCrossS))
      if (Math.isZero(qMinusPCrossR)) // if vectors are collinear
        return collinear()
      else // parallel therefore non-intersecting
        return Parallel()
    else { // Not collinear or parallel
      val t = ((q - p) cross s) / rCrossS
      if (0 <= t && t <= 1) {
        val u = qMinusPCrossR / rCrossS
        if (0 <= u && u <= 1) {
          // The point can be calculated with t or u
          return IntersectionPoint(p + (r * t))
        }
      }
    }
    NoIntersection()
  }

  /**
   * Determine if the given vector overlaps (has more than one point in common with) this vector.
   * @param that the other vector
   * @return True if there is an overlap; otherwise false.
   */
  def overlaps(that: EuclideanVector): Boolean = {
    intersection(that) match {
      case _: OverlappingVectors =>true
      case _ => false
    }
  }

  /**
   * Return the direction of the vector as a slope (b,,y,,-a,,y,,)/(b,,x,,-a,,x,,).  A zero value of (b,,x,,-a,,x,,) is
   * treated as a special case depending on the value of the numerator. if b,,y,,-a,,y,, is
   *   - positive <br>return Double.PositiveInfinity
   *   - zero <br>return 0
   *   - negative <br>return Double.NegativeInfinity
   */
  def slope: Double = {
    val xDelta = b.x - a.x
    val yDelta = b.y - a.y
    if (xDelta != 0.0)
      yDelta/xDelta
    else if (yDelta > 0.0)
      Double.PositiveInfinity
    else if (yDelta < 0.0)
      Double.NegativeInfinity
    else
      0.0
  }

  /**
   * The magnitude of the vector as a positive scalar
   */
  def magnitude: Double = {
    val xDelta = b.x - a.x
    val yDelta = b.y - a.y
    java.lang.Math.sqrt(xDelta * xDelta + yDelta * yDelta)
  }
}


