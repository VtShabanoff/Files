package com.skillbox.myfirsthomework

fun main() {
    val firstName: String = "Vitaly"
    val lastName: String = "Shabanov"
    var height: Int = 180
    var weight: Float = 98.8f
    val isChild: Boolean = height < 150 && weight > 40
    val info: String = "First Name = " + firstName + "\nLastName = " +  lastName + "\nHeight = " + height +
    "\nWeight = " + weight + if (!isChild) "\nHe is not child" else "\nHe is child"
    print(info)
}