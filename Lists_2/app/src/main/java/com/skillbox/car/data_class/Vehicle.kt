package com.skillbox.car.data_class

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Vehicle: Parcelable{
    @Parcelize
    data class Car(
        val id: Long,
        val modelName: String,
        val makeCar: String,
        val avatarLink: String
    ): Vehicle()

    @Parcelize
    data class ElectricCar(
        val id: Long,
        var modelName: String,
        val makeCar: String,
        val avatarLink: String,
        val typeCar: String
    ): Vehicle()

}
