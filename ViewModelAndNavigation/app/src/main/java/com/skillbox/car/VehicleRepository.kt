package com.skillbox.car

import androidx.lifecycle.MutableLiveData
import com.skillbox.car.data_class.Vehicle
import kotlin.random.Random

class VehicleRepository {

    fun createVehicle(nameModel: String, nameMake: String, isElectric: Boolean): Vehicle {
        return if (isElectric) {
            Vehicle.ElectricCar(
                id = Random.nextLong(),
                modelName = nameModel,
                makeCar = nameMake,
                avatarLink = LINK_ELECTRIC,
                typeCar = "Электромобиль"
            )
        } else {
            Vehicle.Car(
                id = Random.nextLong(),
                modelName = nameModel,
                makeCar = nameMake,
                avatarLink = LINK_CAR
            )
        }
    }

    fun deleteVehicle(vehicles: List<Vehicle>, id: Long): List<Vehicle>{

        val findByIdVehicle = vehicles.find {
            (it as Vehicle.Car).id == id
        } ?: vehicles.find {
            (it as Vehicle.ElectricCar).id == id
        }
        val positionVehicle = vehicles.indexOf(findByIdVehicle)

        return vehicles.filter{
            it != vehicles[positionVehicle]
        }
    }
    companion object {
        const val LINK_ELECTRIC =
            "https://i0.wp.com/itc.ua/wp-content/uploads/2020/01/mercedes-benz-vision-avtr-3.jpg"
        const val LINK_CAR =
            "https://img1.fonwall.ru/o/ei/cars-toyota-crossover-red.jpeg?route=thumb&h=350"
    }
}