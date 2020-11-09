package classes

import `interface`.SoundAble
import parent_classes.Animal

class Dog(weight: Int, name: String, energy: Int, override val maxAge: Int) : Animal(weight, name, energy)
, SoundAble{
    override fun move() {
        super.move()
        println("$name бежит")
    }

    override fun makeChild(): Dog {
        val child = super.makeChild()
        return Dog(child.weight, child.name, child.energy, child.maxAge)
    }

    override fun makeSound() {
        println("$name Гав - гав!!!...")
    }


}