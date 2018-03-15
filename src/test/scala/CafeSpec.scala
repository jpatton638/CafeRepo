import Cafe._
import org.scalatest.{AsyncWordSpec, MustMatchers}

import scala.concurrent.Future

class CafeSpec extends AsyncWordSpec with MustMatchers {

  "Cafe" when {

    "heat is called" must {

      "return Water when called" in {
        heat(Water()) map ( f =>
            assert(f == Water(40))
          )
      }

      "return Water with default 40 if no temperature given" in {
        heat(Water()) map(f =>
            assert(f == Water(40))
          )
      }

      "return Water with given temperature" in {
        heat(Water(), 80.0) map(f =>
          assert(f == Water(80))
          )
      }
    }

    "grind is called" must {

      "return GroundCoffee when given Arabica Beans" in {
        grind("Arabica Beans") map(f =>
            assert(f == GroundCoffee("Arabica Beans"))
          )
      }

      "throw a IllegalArgumentException when given anything else" in {
        recoverToSucceededIf[IllegalArgumentException] {
          grind("Baked Beans") map(f =>
              assert(f == GroundCoffee("Arabica Beans"))
            )
        }
      }

    }

    "frothMilk is called" must {

      "return frothed milk when given whole milk" in {
        frothMilk(new WholeMilk) map(f =>
            assert(f == FrothedMilk(new WholeMilk))
          )
      }

      "throw a IllegalArgumentException when given semi skimmed milk" in {
        recoverToSucceededIf[IllegalArgumentException] {
          frothMilk(new SemiSkimmedMilk) map(f =>
              assert(f == FrothedMilk(new WholeMilk))
            )
        }
      }

    }

    "brew is called" must {

      "return Coffee when water is 40 degrees or above" in {

        brew(Water(40), GroundCoffee("Arabica Beans")) map(f =>
            assert(f == Coffee(GroundCoffee("Arabica Beans"), None, 40))
          )
      }

      "return coffee with milk if milk wanted, reducing temp by 5 degrees" in {

        brew(Water(40), GroundCoffee("Arabica Beans"), Some(FrothedMilk(new WholeMilk))) map(f =>
            assert(f == Coffee(GroundCoffee("Arabica Beans"), Some(FrothedMilk(new WholeMilk)), 35))
          )
      }

      "throw BrewingExeption when temperature is too low" in {
        val ex: Future[BrewingException] = recoverToExceptionIf[BrewingException] {
          brew(Water(39), GroundCoffee("Arabica Beans"))
        }
        ex.map( e =>
          e.getMessage mustEqual "The water is too cold"
        )
      }
    }
  }
}
