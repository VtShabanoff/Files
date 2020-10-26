package classes

import parent_classes.Animal

class Dog(energy: Int, weight: Int, name: String, maxAge: Int) : Animal(energy, weight, name, maxAge) {
    override fun move() {
        super.move()
        println("$name бежит")
    }

    override fun makeChild(): Dog {
        super.makeChild()
        return Dog(energy, weight, name, maxAge)
    }




}