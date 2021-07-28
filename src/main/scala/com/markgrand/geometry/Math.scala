package com.markgrand.geometry

/**
 * Math functions used elsewhere in this package.
 */
private[geometry] object Math {
  val Delta = 1e-10d

  def isZero(d: Double): Boolean = d.abs < Delta
}
