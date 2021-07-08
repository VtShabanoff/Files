package com.skillbox.car.data_class

sealed class Vehicle{

    data class Car(
        val modelName: String,
        val makeCar: String,
        val avatarLink: String
    ): Vehicle()

    data class ElectricCar(
        val modelName: String,
        val makeCar: String,
        val avatarLink: String,
        val typeCar: String
    ): Vehicle()

}
