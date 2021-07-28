package com.markgrand.geometry

/**
 * Math functions used elsewhere in this package.
 */
private[geometry] object Math {
  val Delta = 1e-10d

  def isZero(d: Double): Boolean = d.abs < Delta

  def isEqual(d1: Double, d2: Double): Boolean = (d1-d2).abs < Delta
}
