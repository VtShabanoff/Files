package classes

import parent_classes.Animal

class Fish(energy: Int, weight: Int, name: String, maxAge: Int) : Animal(energy, weight, name, maxAge) {
    override fun move() {
        super.move()
        println("$name плывет")
    }

    override fun makeChild(): Fish {
        super.makeChild()
        return Fish(energy, weight, name, maxAge)
    }
}