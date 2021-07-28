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
   * @return NoIntersection, LineSegmentsIntersection or OverlappingLineSegment
   * @see NoIntersection
   * @see LineSegmentsIntersection
   * @see OverlappingLineSegment
   */
  def intersection(that: EuclideanVector): VectorIntersection = {
    val p = a
    val q = that.a
    val r = b - p
    val s = that.b - q
    val rCrossS = r cross s
    val qMinusPCrossR = (q - p) cross r

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
    else {
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
}


