object Cafe {

  case class Water(temperature: Double = 20.0)
  case class GroundCoffee(name: String)
  case class Coffee()
  case class FrothedMilk(milk: Milk)

  trait Milk

  case class WholeMilk() extends Milk
  case class SemiSkimmedMilk() extends Milk

  type CoffeeBeans = String

  case class BrewingException() extends Exception("The water is too cold")

  def heat(water: Water, temperature: Double = 40.0): Water = {
    Water(temperature)
  }

  def grind(beans: CoffeeBeans): GroundCoffee = {
    if (beans.toLowerCase == "arabica beans") {
      GroundCoffee(beans)
    } else {
      throw new IllegalArgumentException
    }
  }

  def frothMilk(milk: Milk): FrothedMilk = {
    if (milk.isInstanceOf[WholeMilk]) {
      FrothedMilk(milk)
    } else {
      throw new IllegalArgumentException
    }
  }

  def brew(water: Water, coffee: GroundCoffee): Coffee = {
    if (water.temperature >= 40) {
      Coffee()
    } else {
      throw new BrewingException
    }
  }
}
