package com.skillbox.car.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.car.data_class.Vehicle
import com.skillbox.car.databinding.ItemCarBinding

class CarAdapterDelegate(
    private val onItemClick: (position: Int) -> Unit
): AbsListItemAdapterDelegate<Vehicle.Car, Vehicle, CarAdapterDelegate.CarHolder>() {

    override fun isForViewType(item: Vehicle, items: MutableList<Vehicle>, position: Int): Boolean {
        return item is Vehicle.Car
    }

    override fun onCreateViewHolder(parent: ViewGroup): CarHolder {
        return CarHolder(ItemCarBinding.inflate(
            LayoutInflater.from(parent.context), parent, false), onItemClick)
    }

    override fun onBindViewHolder(
        item: Vehicle.Car,
        holder: CarHolder,
        payloads: MutableList<Any>,
    ) {
        holder.bind(item)
    }

    class CarHolder(
        binding: ItemCarBinding,
        onItemClick: (position: Int) -> Unit,
    ) : BaseAdapterHolder(binding, onItemClick){

        fun bind(car: Vehicle.Car){
            bindMainInfo(car.modelName, car.makeCar, car.avatarLink)
            typeCar.isVisible = false
        }
    }
}