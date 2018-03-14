object Cafe {
  case class Water(temperature: Double)
  case class CoffeeBeans(name: String)
  case class Milk(name: String)
  case class Coffee(name: String, milky: Option[Milk])

  type GroundCoffee = String
  type FrothedMilk = String

  def heat(water: Water, temperature: Double = 20.0): Water = {
    ???
  }

  def grind(beans: CoffeeBeans): GroundCoffee = {
    ???
  }

  def frothMilk(milk: Milk): FrothedMilk = {
    ???
  }

  def brew(water: Water, coffee: GroundCoffee): Coffee = {
    ???
  }
}
