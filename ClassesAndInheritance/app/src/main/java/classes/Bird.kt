package classes

import parent_classes.AgeAnimal
import parent_classes.Animal

class Bird(energy: Int, weight: Int, name: String, maxAge: Int) : Animal(energy, weight, name, maxAge) {
    override fun move() {
        super.move()
        println("$name летит")
    }

    override fun makeChild(): Bird {
        super.makeChild()
        return Bird(energy, weight, name, maxAge)
    }
}