package classes

import `interface`.SoundAble
import parent_classes.Animal

class Bird(weight: Int, name: String, energy: Int, override val maxAge: Int) : Animal(weight, name, energy)
, SoundAble{

    override fun move() {
        super.move()
        println("$name летит")
    }

    override fun makeChild(): Bird {
        val child = super.makeChild()
        return Bird(child.weight, child.name, child.energy, child.maxAge)
    }

    override fun makeSound() {
        println("$name Чирик-Чирик.....")
    }
}