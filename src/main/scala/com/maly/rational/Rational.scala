package com.maly.rational

class Rational(n: Int, d: Int) {
  require(d != 0, "rational numbers may not have a zero in the denominator")

  private val g = gcd(n.abs, d.abs)
  val numer: Int = n / g
  val denom: Int = d / g

  // auxiliary construction for whole numbers
  def this(n: Int) = this(n, 1)

  def + (that: Rational): Rational =
    new Rational(
      this.numer * that.denom + that.numer * this.denom,
      this.denom * that.denom
    )

  def - (that: Rational): Rational =
    new Rational(
      this.numer * that.denom - that.numer * this.denom,
      this.denom * that.denom
    )

  def * (that: Rational): Rational =
    new Rational(
      this.numer * that.numer,
      this.denom * that.denom
    )

  def / (that: Rational): Rational =
    new Rational(
      this.numer * that.denom,
      this.denom * that.numer
    )

  override def toString = s"$numer/$denom"

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
}
