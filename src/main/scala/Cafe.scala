object Cafe {
  case class Water(temperature: Double = 20.0)
  case class GroundCoffee(name: String)
  case class Milk(name: String)
  case class Coffee(name: String, milky: Option[Milk])

  type CoffeeBeans = String
  type FrothedMilk = String



  def heat(water: Water, temperature: Double = 40.0): Water = {
    Water(temperature)
  }

  def grind(beans: CoffeeBeans): GroundCoffee = {
    GroundCoffee(beans)
  }

  def frothMilk(milk: Milk): FrothedMilk = {
    ???
  }

  def brew(water: Water, coffee: GroundCoffee): Coffee = {
    ???
  }
}
