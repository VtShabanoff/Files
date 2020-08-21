package com.skillbox.function

import kotlin.math.sqrt

fun main() {
    print(solveEquation(20, 30, 11))
}

fun solveEquation(a: Int, b: Int, c: Int): Any {
    val isEqualityABC: Boolean = (a == b || a == c) || (b == a || b == c) || (c == a || c == b)// числа не должны равнятся друг другу
    val discriminant = (b * b) - 4 * a * c // дискриминант
    val sqrtDsrmt = sqrt(discriminant.toDouble()) // выводим дискриминант из под корня
    val error = "При решении квардратных уравнений: \n" +
            "1. - \"а\" не должно быть равным \"0\"\n" +
            "2. - числа \"а\", \"b\"  и \"с\" не должны быть равными друг другу"
    val noRoots = "Уравнение не имеет корней"

    return if (a == 0 || isEqualityABC) error // условие с ошибкой
    else if (discriminant > 0) {
        ((-b + sqrtDsrmt) / (2 * a)) * ((-b - sqrtDsrmt) / (2 * a))
    } // если уравнение имеет 2 корня
    else if (discriminant == 0) {
        -b / 2 * a
    }// если уравниние имеет 1 корень
    else "уровнение не имеет корней" // idea подсказала прекрасную штуку с ретерном
}


