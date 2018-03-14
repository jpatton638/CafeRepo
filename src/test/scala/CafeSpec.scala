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



    }

    "brew is called" must {

    }
  }
}
