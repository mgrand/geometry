package com.markgrand.geometry

/**
 * Represent a single point
 * @param x abscissa
 * @param y ordinate
 *
 * @author Mark Grand
 */
case class Point (x: Double, y: Double) {
  /**
   * The difference between two points
   * @param that the other point
   * @return the difference
   */
  def - (that: Point): Point = Point(this.x - that.x, this.y - that.y)

  /**
   * The sum of two points
   *
   * @param that the magnitude
   * @return the sum as a point
   */
  def + (that: Point): Point = Point(this.x + that.x, this.y + that.y)

  /**
   * Multiple the point by a scalar
   * @param n the scalar
   * @return the product
   */
  def *(n: Double): Point = Point(x*n, y*n)

  /**
   * Define the 2-dimensional vector cross product v × w to be v,,x,,w,,y,, − v,,y,,w,,x,,
   * as described in
   * [[https://stackoverflow.com/questions/563198/how-do-you-detect-where-two-line-segments-intersect]]
   *
   * @param that the other point
   * @return the cross product
   */
  def cross(that: Point): Double = this.x*that.y - this.y*that.x

  /**
   * The dot product
   * @param that the other point
   * @return the dot product
   */
  def dot(that: Point): Double = this.x*that.x + this.y*that.y
}
