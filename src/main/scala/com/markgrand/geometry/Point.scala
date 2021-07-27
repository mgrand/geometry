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

  /**
   * The sum of a point and a magnitude
   *
   * @param magnitude2d the magnitude
   * @return the sum as a point
   */
  def + (magnitude2d: Magnitude2d): Point = Point(x + magnitude2d.x, y + magnitude2d.y)

  /**
   * Define the 2-dimensional vector cross product v × w to be v<sub>x</sub>w<sub>y</sub> − v<sub>y</sub>w<sub>x</sub>
   * as described in
   * <a href="https://stackoverflow.com/questions/563198/how-do-you-detect-where-two-line-segments-intersect">stack
   * overflow</a>
   *
   * @param that the other point
   * @return the cross product
   */
  def cross(that: Point): Double = this.x*that.y - this.y*that.x
}
