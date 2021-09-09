package com.skillbox.car

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.car.data_class.Vehicle

class VehicleListViewModel: ViewModel() {

    private val vehiclesLiveData = MutableLiveData<List<Vehicle>>()

    val vehicles: LiveData<List<Vehicle>>
        get() = vehiclesLiveData

    private lateinit var textModel: String
    private lateinit var textMake: String
    private var isElectric = false

    private val vehicleRepository = VehicleRepository()

    fun deleteVehicle(position: Int){
        vehiclesLiveData
            .postValue(vehicleRepository.deleteVehicle(vehiclesLiveData.value.orEmpty(), position))
    }

    fun addVehicle(){
        val newVehicle = vehicleRepository.createVehicle(textModel, textMake, isElectric)
        val updatedList = listOf(newVehicle) + vehiclesLiveData.value.orEmpty()
        vehiclesLiveData.postValue(updatedList)
    }

    fun getVehicles() = vehiclesLiveData.value.orEmpty()
}