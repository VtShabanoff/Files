package com.skillbox.car.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.skillbox.car.R
import com.skillbox.car.data_class.Vehicle
import com.skillbox.car.databinding.ItemCarBinding
import com.skillbox.car.databinding.ItemElictricCarBinding

abstract class BaseAdapterHolder(
    binding: ViewBinding,
    onItemClick: (position: Int) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {


        private lateinit var nameModel: TextView
        private lateinit var nameMake: TextView
        lateinit var typeElectricCar: TextView
        lateinit var typeCar: TextView
        private lateinit var avatarTypeCar: ImageView

        init {
            when (binding){
                is ItemCarBinding -> {
                    nameModel = binding.nameModelTextView
                    nameMake = binding.nameMakeTextView
                    avatarTypeCar = binding.avatarImageView
                    typeCar = binding.typeCar
                }
                is ItemElictricCarBinding -> {
                    nameModel = binding.nameModelTextView
                    nameMake = binding.nameMakeTextView
                    avatarTypeCar = binding.avatarImageView
                    typeElectricCar = binding.typeElectricCar
                }
            }
            binding.root.setOnClickListener {
                onItemClick(absoluteAdapterPosition)
            }
        }
        fun bindMainInfo(model: String, make: String, avatarLink: String){
            nameModel.text = model
            nameMake.text = make

            Glide.with(itemView)
                .load(avatarLink)
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.ic_baseline_file_download_24)
                .into(avatarTypeCar)
        }


}