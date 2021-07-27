package com.markgrand.geometry

/**
 * Represent a magnitude as its x and y components.
 */
case class Magnitude2d(x: Double, y: Double) {
  /**
   * Multiply a 2d magnitude by a scalar
   * @param m the multiplier
   * @return the product
   */
  def times(m:Double): Magnitude2d = Magnitude2d(x*m, y*m)
}
