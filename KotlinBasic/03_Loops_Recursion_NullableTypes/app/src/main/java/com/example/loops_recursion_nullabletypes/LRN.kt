package com.example.loops_recursion_nullabletypes

fun main() {
    val a = readLine()?.toIntOrNull() ?: return
    val b = readLine()?.toIntOrNull() ?: return
    //inputInt()
    //printPositiveNumbersAndSumNumbers()
   // println(gCD(a, b))

}

fun inputInt() {
    println("Введите число -")
    while (true){
        val lineInt = readLine()?.toIntOrNull()
        if (lineInt == null) continue else break
    }

}
fun printPositiveNumbersAndSumNumbers(){
    var count = 0;
    var sum = 0
    var countPositiveNumbers = 0

    while (count < 5){
        println("Введите числа положительные или отрицательные типа Int")
        var lineNumberInt = readLine()?.toIntOrNull()

        if (lineNumberInt != null &&  lineNumberInt >= 0) {
           println(" число положительное")
            countPositiveNumbers++
        } else if (lineNumberInt != null && lineNumberInt < 0)println(" число отрицательное")

        else println("Вы ввели не число")

        count++

        if (lineNumberInt != null) {
            sum += lineNumberInt
        }
        if (count == 5) break
    }

    println("количество введенных положительных чисел = $countPositiveNumbers")
    println("сумма всех введенных чисел  = $sum")
}
tailrec fun gCD(a: Int, b: Int): Int? {

    return if (b == 0){
       a
    } else gCD(b, a % b)

}
