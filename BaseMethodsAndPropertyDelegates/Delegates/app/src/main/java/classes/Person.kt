package classes

import kotlin.random.Random

class Person(val name: String, var weight: Int, var height: Int) {

    private val pets: HashSet<Animal> by MyDelegate(HashSet())

    fun buyPet(){

        val names = arrayListOf("Тарзан", "Мухтар", "Хома", "Гоша", "Кеша", "Конар", "Мурзик",
                "Гриша", "Кузя", "Вотер", "Аква").random()

        val randWeight = Random.nextInt(101) + 10

        val randEnergy = Random.nextInt(160) + 30

        pets.add(Animal(names, randWeight, randEnergy))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        if (name != other.name) return false
        if (weight != other.weight) return false
        if (height != other.height) return false
        if (pets != other.pets) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + weight
        result = 31 * result + height
        result = 31 * result + pets.hashCode()
        return result
    }

    override fun toString(): String {
        return "Person(name='$name', weight=$weight, height=$height, pets=$pets)"
    }
}