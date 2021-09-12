package com.skillbox.car.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.car.data_class.Vehicle
import com.skillbox.car.databinding.ItemElictricCarBinding

class ElectricCarAdapterDelegate(
    private val onItemLongClick: (id: Long) -> Unit,
    private val onItemClick: (position: Int) -> Unit
): AbsListItemAdapterDelegate<Vehicle.ElectricCar, Vehicle, ElectricCarAdapterDelegate.ElectricCarHolder>() {

    override fun isForViewType(item: Vehicle, items: MutableList<Vehicle>, position: Int): Boolean {
        return item is Vehicle.ElectricCar
    }

    override fun onCreateViewHolder(parent: ViewGroup): ElectricCarHolder {
        return  ElectricCarHolder(ItemElictricCarBinding.inflate(
            LayoutInflater.from(parent.context), parent, false), onItemLongClick, onItemClick)
    }

    override fun onBindViewHolder(
        item: Vehicle.ElectricCar,
        holder: ElectricCarHolder,
        payloads: MutableList<Any>,
    ) {
        holder.bind(item)
    }

    class ElectricCarHolder(
        binding: ItemElictricCarBinding,
        onItemLongClick: (id: Long) -> Unit,
        onItemClick: (position: Int) -> Unit
    ): BaseAdapterHolder(binding, onItemLongClick, onItemClick){


        fun bind(eCar: Vehicle.ElectricCar){
            bindMainInfo(eCar.modelName, eCar.makeCar, eCar.avatarLink, eCar.id)
            typeElectricCar.text = "${eCar.typeCar}"
        }
    }
}