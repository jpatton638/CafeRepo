import models.{FrothedMilk, Water}

trait Coffee {
  val temperature = 0.0
  val coffeeType: String
}

case class GroundCoffee(name: String) extends Coffee {
  override val coffeeType: String = name
}
case class Espresso(groundCoffee: GroundCoffee, water: Water, override val temperature: Double) extends Coffee {
  override val coffeeType: String = "Espresso"
}
case class Cappuccino(espresso: Espresso, milk: FrothedMilk, override val temperature: Double) extends Coffee {

  override val coffeeType: String = "Cappuccino"
  override def toString: String = f"You have brewed the following coffee: $coffeeType at $temperature%2.2f degrees with ${milk.milkType}%s Milk"

}