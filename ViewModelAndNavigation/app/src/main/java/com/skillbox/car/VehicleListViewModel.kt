package com.skillbox.car

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.car.data_class.Vehicle

class VehicleListViewModel: ViewModel() {

    private val vehiclesLiveData = MutableLiveData<List<Vehicle>>()
    private val showToastMutableLiveData = SingleLiveEvent<Unit>()

    val vehicles: LiveData<List<Vehicle>>
        get() = vehiclesLiveData

    val showToastLiveData: LiveData<Unit>
        get() = showToastMutableLiveData

    private val vehicleRepository = VehicleRepository()

    fun deleteVehicle(id: Long){
        vehiclesLiveData
            .postValue(vehicleRepository.deleteVehicle(vehiclesLiveData.value.orEmpty(), id))

        showToastMutableLiveData.postValue(Unit)





    }

    fun addVehicle(modelCar: String, makeCar: String, isElectricCar: Boolean){
        val newVehicle = vehicleRepository.createVehicle(modelCar, makeCar, isElectricCar)
        val updatedList = listOf(newVehicle) + vehiclesLiveData.value.orEmpty()
        vehiclesLiveData.postValue(updatedList)
    }

    fun getVehicles() = vehiclesLiveData.value.orEmpty()
}