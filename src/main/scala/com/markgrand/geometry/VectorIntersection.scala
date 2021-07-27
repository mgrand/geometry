package com.markgrand.geometry

/**
 * Represent the intersection of two line segments
 * @author Mark Grand
 */
sealed abstract class VectorIntersection

/**
 * Represent the intersection of two line segments that do not share any common points.
 */
case class NoIntersection() extends VectorIntersection

/**
 * Represent the common point shared by two line segments that share a single point. They may intersect or be colinear
 * and share a single overlapping point.
 * @param point the common point
 */
case class IntersectionPoint(point: Point) extends VectorIntersection

/**
 * Represent an overlap between two overlapping colinear line segments.
 */
case class OverlappingVectors() extends VectorIntersection
