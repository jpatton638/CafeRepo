import Cafe._
import org.scalatest.{MustMatchers, WordSpec}

class CafeSpec extends WordSpec with MustMatchers {

  "Cafe" when {

    "heat is called" must {

      "return Water when called" in {
        heat(Water()) mustEqual Water(40)
      }

      "return Water with default 40 if no temperature given" in {
        heat(Water()) mustEqual Water(40)
      }

      "return Water with given temperature" in {
        heat(Water(), 80.0) mustEqual Water(80)
      }
    }

    "grind is called" must {

      "return GroundCoffee when given Arabica Beans" in {
        grind("Arabica Beans") mustEqual GroundCoffee("Arabica Beans")
      }

      "throw a IllegalArgumentException when given anything else" in {
        intercept[IllegalArgumentException] {
          grind("baked beans")
        }
      }

    }

    "frothMilk is called" must {

      "return frothed milk when given whole milk" in {
        frothMilk(new WholeMilk) mustEqual FrothedMilk(new WholeMilk)
      }

      "throw a IllegalArgumentException when given semi skimmed milk" in {
        intercept[IllegalArgumentException] {
          frothMilk(new SemiSkimmedMilk)
        }
      }

    }

    "brew is called" must {

      "return Coffee when water is 40 degrees or above" in {

        brew(Water(40), GroundCoffee("Arabica Beans")) mustEqual Coffee()
      }

      "throw BrewingExeption when temperature is too low" in {
        val ex = intercept[BrewingException] {
          brew(Water(39), GroundCoffee("Arabica Beans"))
        }
        ex.getMessage mustEqual "The water is too cold"
      }
    }
  }
}
