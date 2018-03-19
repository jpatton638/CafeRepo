package models

trait Milk

case class WholeMilk() extends Milk
case class SemiSkimmedMilk() extends Milk
case class FrothedMilk(milk: Milk) extends Milk