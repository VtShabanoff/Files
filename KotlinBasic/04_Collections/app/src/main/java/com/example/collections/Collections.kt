package com.example.collections

fun main() {
    val listNumber = mutableListOf<String>()
    enterPhoneNumber(listNumber)
    val filterList = listNumber.filter { it.contains("+7", false) }
    val setNumbers = filterList.toSet()
    enterName(setNumbers).forEach{ (key, value) ->
        println("Человек: $value. Номер телефона: $key")
    }
    println("Список номеров введенных пользователем =>")
    printList(listNumber)
    println("Отфильтрованный список  по номерам +7 =>")
    printList(filterList as MutableList<String>)
    println("Список уникальных номеров телефонов =>")
    printList(setNumbers)
    println("Размер множества => ${setNumbers.size}")
    println("Сумма длин всех номеров телефонов => ${listNumber.sumBy { it.length }}")
}
fun enterPhoneNumber(listNumber: MutableList<String> ): List<String> {
    println("Введите номер телефона")
    while (true) {
        val enterNumber = readLine()
        if (enterNumber != null) {
            listNumber.add(enterNumber)
        }
        if (enterNumber == "q"){ // здесь выходим из цикла при введении буквы q
            listNumber.removeAt(listNumber.lastIndex)// удаляю последний элемент так как он так же запоминается и печатается , может есть и другой способ
            break
        }
    }
    return listNumber
}

fun printList(list: Collection<String>){
    list.forEach{ println(it)}
}

fun enterName(list: Collection<String>): Map<String, String>{
    val map = mutableMapOf<String, String>()
    println("Введите имя")
    for (item in list){
        map[item] = readLine().toString()
    }
    return map
}

