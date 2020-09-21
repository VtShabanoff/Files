package com.example.loops_recursion_nullabletypes

fun main() {

    println(inputInt())
    printPositiveNumbersAndSumNumbers()
    println("Введите два числа для функции gCD - вычисления НОД =>")
    val a = readLine()?.toIntOrNull() ?: return
    val b = readLine()?.toIntOrNull() ?: return
    println(gCD(a, b))

}

fun inputInt():Int {
    println("Введите число для функции inputInt =>")
    while (true){
        return readLine()?.toIntOrNull() ?: continue
    }


}
fun printPositiveNumbersAndSumNumbers(){
    var count = 0;
    var sum = 0
    var countPositiveNumbers = 0

    println("Функция printPositiveNumbersAndSumNumbers \nВведите число для итерации =>")
    val a = readLine()?.toIntOrNull() ?: return
    while (count < a){
        println("Введите числа положительные или отрицательные типа Int =>")
        val b = readLine()?.toIntOrNull()

        if (b != null) {
            when {
                b >= 0 -> {
                    println(" число положительное")
                    countPositiveNumbers++
                }
                b < 0 -> {
                    println(" число отрицательное")
                }
                else -> {println("Вы ввели не число")}
            }
        }

        count++

        if (b != null) {
            sum += b
        }
    }

    println("количество введенных положительных чисел = $countPositiveNumbers")
    println("сумма всех введенных чисел  = $sum")
}
tailrec fun gCD(a: Int, b: Int): Int {
    val sum = a + b
    println("сумма введенных чисел = $sum")
    return if (b == 0){
       a
    } else gCD(b, a % b)
    
}
