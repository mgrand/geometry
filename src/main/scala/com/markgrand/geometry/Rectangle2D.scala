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

}

object Rectangle2D {
  def apply(a: Point, b: Point, c: Point, d: Point): Rectangle2D = new Rectangle2D(a, b, c, d)
}
