package parent_classes

import kotlin.random.Random

abstract class Animal(
        weight: Int,
        val name: String,
        energy: Int
): AgeAnimal() {

    var weight = weight
        private set(value){
            field = if (value < 0) 0 else value
            }

    var energy = energy
        private set(value){
            field = if (value < 0) 0 else value
        }

    var age: Int = 0
        private set(value){
            field = if (value < 0) 0 else value
        }


    val isTooOld: Boolean
        get() = age >= maxAge
    /*val isOld: Boolean
        get() = isTooOld || weight <= 0 || energy <= 0*/

    fun sleep(){
        if (isTooOld) return
        println("$name спит")
        energy += 5
        age += 1
    }
    fun eat(){
        if (isTooOld) return
        println("$name ест")
        energy += 3
        weight++
        incrementAgeSometimes()
    }
    open fun move(){
        if (isTooOld || energy == 0 || weight == 0) return
        println("$name двигается")
        energy -= 5
        weight--
        incrementAgeSometimes()

    }
    open fun makeChild(): Animal{
        val energy = Random.nextInt(10) +1
        val weight = Random.nextInt(5) +1
        println("Родилось новое животное: \nИмя: $name\nЭнергия: $energy\nВес: $weight")
        return object : Animal(weight, name, energy){
            override val maxAge: Int = this@Animal.maxAge
        }
    }
    private fun incrementAgeSometimes() {
        if (Random.nextBoolean()) {
            age++}
    }


}