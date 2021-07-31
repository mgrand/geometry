package com.markgrand.geometry

/**
 * Math functions used elsewhere in this package.
 *
 * @author Mark Grand
 */
private[geometry] object Math {
  /**
   * Equality testing functions in this class will treat values as equal if they differ by less than this amount.
   */
  val Delta = 1e-10d

  def isZero(d: Double): Boolean = d.abs < Delta

  def isEqual(d1: Double, d2: Double): Boolean = (d1-d2).abs < Delta
}
