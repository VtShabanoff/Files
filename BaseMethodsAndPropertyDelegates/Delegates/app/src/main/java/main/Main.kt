package main

import classes.Person

fun main() {
    val man1 = Person("Вася", 80, 179)
    val man2 = Person("Петя", 80, 179)
    val man3 = Person("Жора", 80, 179)
    val man4 = Person("Вася", 80, 179)
    val hashSet = hashSetOf(
            man1,
            man4,
            man2
    )

    hashSet.forEach { println(it) }


    man1.buyPet()
    man1.buyPet()
    man2.buyPet()
    man2.buyPet()
    man2.buyPet()
    man3.buyPet()
    man3.buyPet()
    man3.buyPet()
    man3.buyPet()

}