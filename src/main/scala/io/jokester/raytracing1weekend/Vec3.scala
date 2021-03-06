package io.jokester.raytracing1weekend

import scala.util.Random

case class Vec3(x: Double, val y: Double, val z: Double) {
  def +(that: Vec3): Vec3 = Vec3(x + that.x, y + that.y, z + that.z)
  def -(that: Vec3): Vec3 = Vec3(x - that.x, y - that.y, z - that.z)
  def *(m: Double): Vec3  = Vec3(x * m, y * m, z * m)
  def /(d: Double): Vec3  = Vec3(x / d, y / d, z / d)

  def length: Double    = Math.sqrt(squareSum)
  def squareSum: Double = dot(this)
  def unit: Vec3        = this / length

  def dot(that: Vec3): Double = x * that.x + y * that.y + z * that.z
  def cross(that: Vec3): Vec3 =
    Vec3(
      y * that.z - z * that.y,
      z * that.x - x * that.z,
      x * that.y - y * that.x
    )
  def cosTo(that: Vec3): Double = dot(that) / length / that.length
}

object Vec3 {
  @scala.annotation.tailrec
  def randomUnit(): Vec3 = {
    val r = Vec3(
      randomDouble(-1, 1),
      randomDouble(-1, 1),
      randomDouble(-1, 1)
    )
    if (r.length < 1) r else randomUnit()
  }

  @inline
  private def randomDouble(min: Double, max: Double): Double =
    min + (max - min) * Random.nextDouble()
}
object VecOperators {
  case class WrappedDouble(d: Double) extends AnyVal {
    def *(v: Vec3): Vec3 = v * d
  }
}
