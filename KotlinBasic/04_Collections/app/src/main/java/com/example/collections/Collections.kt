package com.example.collections

fun main() {

    val filterListNumbers = enterPhoneNumber(5).filter { it.startsWith("+7") }
    filterListNumbers.forEach { println("Список номеров начинающихся с +7 => $it") }
    val setNumbers = filterListNumbers.toSet()
    setNumbers.forEach { println("список уникальных номеров => $it") }
    println("размер множества => ${setNumbers.size}")
    println("сумма всех номеров телефона => ${setNumbers.sumBy { it.length }} ")
    enterName(setNumbers).forEach{ (key, value) ->
        println("Человек: $value. Номер телефона: $key")
    }

}
fun enterPhoneNumber(count: Int = 0): List<String> {
    val listNumbers = mutableListOf<String>()
    println("Введите номер телефона")
    while (listNumbers.size < count) {
        val line = readLine().toString()
        listNumbers.add(line)
    }
    return listNumbers
}

fun enterName(list: Collection<String>): Map<String, String>{
    val map = mutableMapOf<String, String>()
    println("Введите имя")
    for (item in list){
        map[item] = readLine().toString()
    }
    return map
}

