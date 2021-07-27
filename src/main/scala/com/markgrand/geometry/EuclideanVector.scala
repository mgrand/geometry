package com.markgrand.geometry

import com.markgrand.geometry.EuclideanVector.isZero

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
  def intersectionPoint(that: EuclideanVector): VectorIntersection = {
    val p = a
    val q = that.a
    val r = b - p
    val s = that.b - q
    val rCrossS = r cross s
    val qMinusPCrossR = (q - p) cross r

    def collinear() = {
      val qMinusPDotR = (q - p) dot r
      if ((0 <= qMinusPDotR && qMinusPDotR <= (r dot r)) || (0 <= ((p - q) dot s) && (((p - q) dot s) <= (s dot s))))
        OverlappingVectors()
      else
        NoIntersection()
    }

    if (isZero(rCrossS))
      if (isZero(qMinusPCrossR)) // if vectors are collinear
        return collinear()
      else // parallel and non-intersecting
        return NoIntersection()
    else {
      val t = ((q - p) cross s) / rCrossS
      if (0 <= t && t <= 1) {
        val u = qMinusPCrossR / rCrossS
        if (0 <= t && t <= 1)
          return IntersectionPoint(p + (r * t))
      }
    }
    NoIntersection()
  }
}

object EuclideanVector {
  private def isZero(d: Double): Boolean = d < 1e-10d
}
