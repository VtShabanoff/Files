package main

import `interface`.SoundAble
import classes.Bird
import classes.Dog
import classes.Fish
import classes.Zoo
import parent_classes.Animal
import kotlin.random.Random

fun main() {
    val zoo = Zoo()
    val listNewChild = mutableListOf<Animal>()
    println("Введите количество циклов жизни животных")
    val read = readLine()?.toIntOrNull() ?: return
    for(i in 1 .. read) {

        zoo.listZoo.forEach {
            when(Random.nextInt(if (it is SoundAble) 5 else 4)){
                0 -> it.eat()
                1 -> it.move()
                2 -> it.sleep()
                3 -> it.makeChild()
                4 -> if (it is SoundAble){
                    it.makeSound()
                }
            }

            listNewChild.add(it.makeChild())
        }
        zoo.listZoo.removeAll{it.isTooOld}
        if (zoo.listZoo.isEmpty()){
            println("Зоопарк пуст!")
            break
        }
    }

        zoo.listZoo.addAll(listNewChild)
        println("Окончательный список животных в зоопарке")
        zoo.listZoo.forEach { println(it.name) }


}