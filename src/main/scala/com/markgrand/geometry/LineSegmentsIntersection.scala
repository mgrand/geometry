package com.markgrand.geometry

/**
 * Represent the intersection of two line segments
 */
sealed abstract class LineSegmentsIntersection

/**
 * Represent the intersection of two line segments that do not share any common points.
 */
case class NoIntersection() extends LineSegmentsIntersection

/**
 * Represent the common point shared by two line segments that share a single point. They may intersect or be colinear
 * and share a single overlapping point.
 * @param point the common point
 */
case class IntersectionPoint(point: Point) extends LineSegmentsIntersection

/**
 * Represent the overlap between two overlapping colinear line segments.
 * @param overlap the intersection of two line segments.
 */
case class OverlappingLineSegment(overlap: LineSegment) extends LineSegmentsIntersection
