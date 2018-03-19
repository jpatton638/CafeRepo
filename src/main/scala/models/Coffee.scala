import models.{FrothedMilk, Water}

trait Coffee {
  val temperature = 0.0
}

case class GroundCoffee(name: String) extends Coffee
case class Espresso(groundCoffee: GroundCoffee, water: Water, override val temperature: Double) extends Coffee
case class Cappuccino(espresso: Espresso, milk: FrothedMilk, override val temperature: Double) extends Coffee