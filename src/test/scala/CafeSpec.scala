import Cafe.{SemiSkimmedMilk, WholeMilk}
import org.scalatest.{MustMatchers, WordSpec}

class CafeSpec extends WordSpec with MustMatchers {

  "Cafe" when {

    "heat is called" must {

      "return Water when called" in {
        Cafe.heat(Cafe.Water()) mustEqual Cafe.Water(40)
      }

      "return Water with default 40 if no temperature given" in {
        Cafe.heat(Cafe.Water()) mustEqual Cafe.Water(40)
      }

      "return Water with given temperature" in {
        Cafe.heat(Cafe.Water(), 80.0) mustEqual Cafe.Water(80)
      }
    }

    "grind is called" must {

      "return GroundCoffee when given Arabica Beans" in {
        Cafe.grind("Arabica Beans") mustEqual Cafe.GroundCoffee("Arabica Beans")
      }

      "throw a IllegalArgumentException when given anything else" in {
        intercept[IllegalArgumentException] {
          Cafe.grind("baked beans")
        }
      }

    }

    "frothMilk is called" must {

      "return frothed milk when given whole milk" in {
        Cafe.frothMilk(new WholeMilk) mustEqual Cafe.FrothedMilk(new WholeMilk)
      }

      "throw a IllegalArgumentException when given semi skimmed milk" in {
        intercept[IllegalArgumentException] {
          Cafe.frothMilk(new SemiSkimmedMilk)
        }
      }

    }
    "brew is called" must {

    }
  }
}
