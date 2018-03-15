import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Cafe extends App {

  case class Water(temperature: Double = 20.0)
  case class GroundCoffee(name: String)
  case class FrothedMilk(milk: Milk)
  case class Coffee(groundCoffee: GroundCoffee, milk: Option[FrothedMilk], temperature: Double)

  trait Milk

  case class WholeMilk() extends Milk
  case class SemiSkimmedMilk() extends Milk

  type CoffeeBeans = String

  case class BrewingException() extends Exception("The water is too cold")

  def heat(water: Water, temperature: Double = 40.0): Future[Water] = Future {
    water.copy(temperature)
  }

  def grind(beans: CoffeeBeans): Future[GroundCoffee] = Future {
    if (beans.toLowerCase == "arabica beans") {
      GroundCoffee(beans)
    } else {
      throw new IllegalArgumentException
    }
  }

  def frothMilk(milk: Milk): Future[FrothedMilk] = Future {
    if (milk.isInstanceOf[WholeMilk]) {
      FrothedMilk(milk)
    } else {
      throw new IllegalArgumentException
    }
  }

  def brew(water: Water, groundCoffee: GroundCoffee, milk: Option[FrothedMilk] = None): Future[Coffee] = Future {
    val temp = water.temperature
    if (temp >= 40) {
      if (milk.isDefined) {
        val findMilk = "([A-Z][a-z]+)".r
        val splitMilk = findMilk.findAllIn(milk.get.milk.toString).mkString(" ")
        println(f"You have brewed the following coffee: Coffee at ${temp-5}%2.2f degrees with $splitMilk%s")
        Coffee(groundCoffee, milk, temp-5)
      } else {
        println(f"You	have brewed the following coffee: Coffee at $temp%2.2f degrees without milk")
        Coffee(groundCoffee, milk, temp)
      }
    } else {
      throw new BrewingException
    }
  }


}