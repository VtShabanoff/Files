package classes

import parent_classes.Animal

class Fish(weight: Int, name: String, energy: Int, override val maxAge: Int) : Animal(weight, name, energy) {

    override fun move() {
        super.move()
        println("$name плывет")
    }

    override fun makeChild(): Fish {
        val child = super.makeChild()
        return Fish(child.weight, child.name, child.energy, child.maxAge)
    }
}