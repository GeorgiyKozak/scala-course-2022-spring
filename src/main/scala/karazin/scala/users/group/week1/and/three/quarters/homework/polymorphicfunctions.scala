package karazin.scala.users.group.week1.and.three.quarters.homework

object polymorphicfunctions extends App :

  /*
    Provide an implementation for the next functions
  */

  val `I₍₂,₄₎⁴`: [`a1`, `a2`, `a3`, `a4`] => `a1` => `a2` => `a3` => `a4` => (`a2`, `a4`) =
    [`a1`, `a2`, `a3`, `a4`] => (`x1`: `a1`) => (`x2`: `a2`) => (`x3`: `a3`) => (`x4`: `a4`) => (`x2`, `x4`)

  val `(f ० g ० h ० i)(x)`: [ε, δ, γ, β, α] => (δ => ε) => ((γ => δ) => ((β => γ) => ((α => β) => (α => ε)))) =
    [ε, δ, γ, β, α] ⇒ (f: (δ ⇒ ε)) ⇒ (g: (γ ⇒ δ)) ⇒ (h: (β ⇒ γ)) ⇒ (i: (α ⇒ β)) ⇒ (x: α) ⇒ f(g(h(i(x))))
end polymorphicfunctions

