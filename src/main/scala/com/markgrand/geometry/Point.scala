package com.markgrand.geometry

/**
 * Represent a single point
 * @param x abscissa
 * @param y ordinate
 */
case class Point (x: Double, y: Double) {
  /**
   * The difference between two points
   * @param that the other point
   * @return the difference as a magnitude
   */
  def - (that: Point): Magnitude2d = Magnitude2d(this.x - that.x, this.y - that.y)
}
