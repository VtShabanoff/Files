package main

import parent_classes.Animal

fun main() {
    var animal = Animal(1, 2, "Шарик", 15)

    while (true){
        with(animal){
            while (true){
                if (isOld){
                    println("Животное слишком старое")
                    println("Вес = $weight Энергия = $energy Возраст = $age")

                    break
                }
                eat()
                sleep()
                move()
            }

        }
        animal = animal.makeChild()

    }


}