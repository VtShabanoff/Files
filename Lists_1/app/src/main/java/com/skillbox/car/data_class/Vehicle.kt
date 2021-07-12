package com.skillbox.car.data_class

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Vehicle: Parcelable{
    @Parcelize
    data class Car(
        val modelName: String,
        val makeCar: String,
        val avatarLink: String
    ): Vehicle()

    @Parcelize
    data class ElectricCar(
        var modelName: String,
        val makeCar: String,
        val avatarLink: String,
        val typeCar: String
    ): Vehicle()

}
