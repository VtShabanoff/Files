package com.example.generics

fun main(){
    val list = listOf(20, 30, 77, 8.0)

    filterEven(list).forEach { println(it) }
}

fun <T: Number> filterEven(list: List<T>): List<T>{
    return list.filter { it.toDouble() % 2.0 == 0.0 }
}