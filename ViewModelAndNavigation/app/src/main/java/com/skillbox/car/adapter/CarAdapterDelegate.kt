package com.skillbox.car.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.car.data_class.Vehicle
import com.skillbox.car.databinding.ItemCarBinding

class CarAdapterDelegate(
    private val onItemLongClick: (id: Long) -> Unit,
    private val onItemClick: (position: Int) -> Unit
): AbsListItemAdapterDelegate<Vehicle.Car, Vehicle, CarAdapterDelegate.CarHolder>() {

    override fun isForViewType(item: Vehicle, items: MutableList<Vehicle>, position: Int): Boolean {
        return item is Vehicle.Car
    }

    override fun onCreateViewHolder(parent: ViewGroup): CarHolder {
        return CarHolder(ItemCarBinding.inflate(
            LayoutInflater.from(parent.context), parent, false), onItemLongClick, onItemClick)
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
        onItemLongClick: (id: Long) -> Unit,
        onItemClick: (position: Int) -> Unit
    ) : BaseAdapterHolder(binding, onItemLongClick, onItemClick){

        fun bind(car: Vehicle.Car){
            bindMainInfo(car.modelName, car.makeCar, car.avatarLink, car.id)
            typeCar.isVisible = false
        }
    }
}