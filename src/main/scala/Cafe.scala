import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Cafe extends App {

  trait Milk
  trait Coffee {
    val temperature = 0.0
  }

  case class Water(temperature: Double = 20.0)
  case class GroundCoffee(name: String)
  case class FrothedMilk(milk: Milk) extends Milk
  case class Espresso(groundCoffee: GroundCoffee, water: Water, override val temperature: Double) extends Coffee
  case class Cappuccino(espresso: Espresso, milk: FrothedMilk, override val temperature: Double) extends Coffee
  case class WholeMilk() extends Milk
  case class SemiSkimmedMilk() extends Milk
  case class BrewingException() extends Exception("The water is too cold")

  type CoffeeBeans = String

  def heat(water: Water, temperature: Double = 40.0): Future[Water] = Future {
    water.copy(temperature)
  }

  def grind(beans: CoffeeBeans): Future[GroundCoffee] = Future {
    beans.toLowerCase match {
      case "arabica beans" => GroundCoffee(beans)
      case "robusta beans" => GroundCoffee(beans)
      case _ => throw new IllegalArgumentException
    }
  }

  def frothMilk(milk: Milk): Future[FrothedMilk] = Future {
    if (milk.isInstanceOf[WholeMilk]) {
      FrothedMilk(milk)
    } else {
      throw new IllegalArgumentException
    }
  }

  def brew(water: Water, groundCoffee: GroundCoffee): Future[Espresso] = Future {
    val temp = water.temperature
    if (temp >= 40) {
      Espresso(groundCoffee, water, temp)
    } else {
      throw new BrewingException
    }
  }

  def prepareCappuccino(): Future[Cappuccino] = {
    val groundCoffee = grind("Arabica Beans")
    val heatWater = heat(Water())
    val frothedMilk = frothMilk(WholeMilk())
    for {
      ground <- groundCoffee
      water <- heatWater
      foam <- frothedMilk
      espresso <- brew(water, ground)
    } yield Cappuccino(espresso, foam, water.temperature-5)

  }

  def printMessage(coffee: Coffee, milk: Milk): Unit = {
    println(f"You have brewed the following coffee: $coffee at ${coffee.temperature}%2.2f degrees with ${milk}%s")
  }

}