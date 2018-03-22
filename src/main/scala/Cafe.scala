import java.util.concurrent.Executors

import models._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Success

object Cafe extends App {

  implicit def context : ExecutionContext = ExecutionContext.fromExecutor(Executors.newCachedThreadPool())

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

    val combination = for {
      ground <- grind("Arabica Beans")
      water <- heat(Water())
      foam <- frothMilk(WholeMilk())
      espresso <- brew(water, ground)
    } yield Cappuccino(espresso, foam, water.temperature-5)

    combination onComplete {
      case Success(cap) => println(cap.toString)
    }
    combination
  }

  prepareCappuccino()
}