package models

trait Milk {
  val milkType: String
}

case class WholeMilk() extends Milk {
  override val milkType: String = "Whole"
}
case class SemiSkimmedMilk() extends Milk {
  override val milkType: String = "Semi-Skimmed"
}
case class FrothedMilk(milk: Milk) extends Milk {
  override val milkType: String = milk.milkType
}