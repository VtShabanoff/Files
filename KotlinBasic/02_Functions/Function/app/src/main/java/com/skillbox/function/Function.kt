package com.skillbox.function

import kotlin.math.sqrt

fun main() {
    print(solveEquation(10.0, 30.0, 11.0))
}

fun solveEquation(a: Double, b: Double, c: Double): Any {
    val isEqualityABC: Boolean = (a == b || a == c) || (b == a || b == c) || (c == a || c == b)
    val discriminant: Double = (b * b) - 4 * a * c
    val sqrtDsrmt: Double = sqrt(discriminant)
    val x1: Double = (-b + sqrtDsrmt) / (2 * a)
    val x2: Double = (-b - sqrtDsrmt) / (2 * a)
    val x3: Double = -b / 2 * a
    val error: String = "При решении квардратных уравнений: \n" +
            "1. - \"а\" не должно быть равным \"0\"\n" +
            "2. - числа \"а\", \"b\"  и \"с\" не должны быть равными друг другу"
    val noRoots: String = "Уравнение не имеет корней"

    if (a == 0.0 || isEqualityABC) {
        return error
    }
    else if (discriminant > 0) {return x1 * x2}
    else if (discriminant == 0.0) {return x3}
    else if (discriminant < 0){return noRoots}
return 0
}


