package com.skillbox.car.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skillbox.car.R
import com.skillbox.car.data_class.Vehicle
import com.skillbox.car.inflate

class CarAdapter(
    private val onItemClick: (position: Int) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var vehicles: List<Vehicle> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            CAR -> BaseHolder.CarHolder(parent.inflate(R.layout.item_car), onItemClick)
            ELECTRIC_CAR -> BaseHolder.ElectricCarHolder(parent.inflate(R.layout.item_elictric_car), onItemClick)
            else ->error("Incorrect viewType $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(vehicles[position]){
            is Vehicle.Car -> CAR
            is Vehicle.ElectricCar -> ELECTRIC_CAR
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is BaseHolder.CarHolder -> {
                val vehicle = vehicles[position].let { it as? Vehicle.Car }
                    ?:error("vehicle position = ${vehicles[position]} is not vehicle")
                holder.bind(vehicle)
            }
            is BaseHolder.ElectricCarHolder -> {
                val vehicle = vehicles[position].let { it as? Vehicle.ElectricCar }
                    ?:error("vehicle position = ${vehicles[position]} is not vehicle")
                holder.bind(vehicle)
            }
        }
    }

    override fun getItemCount(): Int = vehicles.size

    fun updateCars(newCar: List<Vehicle>){
        vehicles = newCar
    }

    abstract class BaseHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ): RecyclerView.ViewHolder(view){

        private val modelCar = view.findViewById<TextView>(R.id.nameModelTextView)
        private val makeCar = view.findViewById<TextView>(R.id.nameMakeTextView)
        private val avatarTypeCar = view.findViewById<ImageView>(R.id.avatarImageView)

        init {
            view.setOnClickListener {
                onItemClick(absoluteAdapterPosition)
            }
        }
        fun bindMainInfo(model: String, make: String, avatarLink: String){

            modelCar.text = model
            makeCar.text = make

            Glide.with(itemView)
                .load(avatarLink)
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.ic_baseline_file_download_24)
                .into(avatarTypeCar)
        }

        class CarHolder(
            view: View,
            onItemClick: (position: Int) -> Unit
        ): BaseHolder(view, onItemClick){
            init {
                view.findViewById<TextView>(R.id.typeCar).isVisible = false
            }
            fun bind(car: Vehicle.Car){
                bindMainInfo(car.modelName, car.makeCar, car.avatarLink)
            }
        }

        class ElectricCarHolder(
            view: View,
            onItemClick: (position: Int) -> Unit
        ): BaseHolder(view, onItemClick){

            private val typeElectricCar = view.findViewById<TextView>(R.id.typeElectricCar)

            fun bind(car: Vehicle.ElectricCar){
                bindMainInfo(car.modelName, car.makeCar, car.avatarLink)
                typeElectricCar.text = "000"
            }
        }
    }
    companion object{
        const val ELECTRIC_CAR = 1
        const val CAR = 2
    }
}