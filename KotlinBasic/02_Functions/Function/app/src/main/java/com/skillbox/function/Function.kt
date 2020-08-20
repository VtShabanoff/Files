package com.skillbox.function

import kotlin.math.sqrt

fun main() {
    print(solveEquation(10, 30, 11))
}

fun solveEquation(a: Int, b: Int, c: Int): Any {
    val isEqualityABC: Boolean = (a == b || a == c) || (b == a || b == c) || (c == a || c == b)// числа не должны равнятся друг другу
    val discriminant = (b * b) - 4 * a * c // дискриминант
    val sqrtDsrmt = sqrt(discriminant.toDouble()) // выводим дискриминант из под корня
    val x1 = (-b + sqrtDsrmt) / (2 * a) // если уравнение имеет 2 корня 1 корень
    val x2 = (-b - sqrtDsrmt) / (2 * a) // если уравнение имеет 2 корня 2 корень
    val x3 = -b / 2 * a// если уравниние имеет 1 корень
    val error = "При решении квардратных уравнений: \n" +
            "1. - \"а\" не должно быть равным \"0\"\n" +
            "2. - числа \"а\", \"b\"  и \"с\" не должны быть равными друг другу"
    val noRoots = "Уравнение не имеет корней"

    if (a == 0 || isEqualityABC) {
        return error // условие с ошибкой
    }
    else if (discriminant > 0) {return x1 * x2}
    else if (discriminant == 0) {return x3}
    else if (discriminant < 0){return noRoots}
return 0 // здесь мне котлин сказал что все таки этот return нужен
}


