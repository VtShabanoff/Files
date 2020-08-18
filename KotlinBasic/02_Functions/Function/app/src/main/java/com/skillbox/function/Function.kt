package com.skillbox.function

fun main() {
    print(solveEquation(2, 3, 4))
}

fun solveEquation(a: Int, b: Int, c: Int): Int {
    return (b * b) - 4 * a * c
}