package com.skillbox.car

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.car.data_class.Vehicle
import com.skillbox.car.fragments.VehicleListFragmentArgs

class VehicleListViewModel: ViewModel() {

    private val vehiclesLiveData = MutableLiveData<List<Vehicle>>()

    val vehicles: LiveData<List<Vehicle>>
        get() = vehiclesLiveData

    private val vehicleRepository = VehicleRepository()

    fun deleteVehicle(position: Int){
        vehiclesLiveData
            .postValue(vehicleRepository.deleteVehicle(vehiclesLiveData.value.orEmpty(), position))
    }

    fun addVehicle(modelCar: String, makeCar: String, isElectricCar: Boolean){
        val newVehicle = vehicleRepository.createVehicle(modelCar, makeCar, isElectricCar)
        val updatedList = listOf(newVehicle) + vehiclesLiveData.value.orEmpty()
        vehiclesLiveData.postValue(updatedList)
    }

    fun getVehicles() = vehiclesLiveData.value.orEmpty()
}