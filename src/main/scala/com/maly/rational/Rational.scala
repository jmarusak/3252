class Rational(n: Int, d: Int) {
  require(d != 0, "rational numbers may not have a zero in the denominator")

  val numer: Int = n
  val denom: Int = d

  // auxiliary construction for whole numbers
  def this(n: Int) = this(n, 1)

  override def toString = s"$n/$d"

  def add(that: Rational): Rational =
    new Rational(
      this.numer * that.denom + that.numer * this.denom,
      this.denom * that.denom
    )
}
