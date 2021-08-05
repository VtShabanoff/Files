package com.skillbox.car.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skillbox.car.data_class.Vehicle

class VehicleAdapter(
    onItemClick: (position: Int) -> Unit
): AsyncListDifferDelegationAdapter<Vehicle>(VehicleDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(CarAdapterDelegate(onItemClick))
            .addDelegate(ElectricCarAdapterDelegate(onItemClick))
    }

    class VehicleDiffUtilCallback: DiffUtil.ItemCallback<Vehicle>(){
        override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
            return when {
                oldItem is Vehicle.Car && newItem is Vehicle.Car -> oldItem.id == newItem.id
                oldItem is Vehicle.ElectricCar && newItem is Vehicle.ElectricCar -> oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
            return oldItem == newItem
        }

    }
}