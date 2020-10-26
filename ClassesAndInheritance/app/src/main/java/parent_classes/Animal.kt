package parent_classes

import kotlin.random.Random

open class Animal(
    energy: Int,
    weight: Int,
    val name: String,
    maxAge: Int
): AgeAnimal(maxAge) {

    var weight = weight
        private set

    var energy = energy
        private set

    var age: Int = 0
        private set


    val isTooOld: Boolean
        get() = age >= maxAge
    val isOld: Boolean
        get() = isTooOld || weight <= 0 || energy <= 0

    fun sleep(){
        println("$name спит")
        energy += 5
        age += 1
    }
    fun eat(){
        println("$name ест")
        energy += 3
        weight++
        incrementAgeSometimes()
    }
    open fun move(){
        println("$name двигается")
        energy -= 5
        weight--
        incrementAgeSometimes()

    }
    open fun makeChild(): Animal{
        val energy = Random.nextInt(10) +1
        val weight = Random.nextInt(5) +1
        age = 0
        println("Родилось новое животное: \nИмя: $name\nВозраст: $age\n" +
                "Энергия: $energy\nВес: $weight\nМаксимальный возраст: $maxAge")
        return Animal(energy, weight, name, maxAge)
    }
    private fun incrementAgeSometimes() {
        if (Random.nextBoolean()) {
            age++}
    }


}