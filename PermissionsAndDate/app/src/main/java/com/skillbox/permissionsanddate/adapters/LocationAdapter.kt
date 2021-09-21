package com.skillbox.permissionsanddate.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skillbox.permissionsanddate.MyLocation
import com.skillbox.permissionsanddate.R
import com.skillbox.permissionsanddate.databinding.ItemLocationBinding
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import kotlin.properties.Delegates

class LocationAdapter(
    private val onItemClick: (id: Long) -> Unit,
) : ListAdapter<MyLocation, LocationAdapter.LocationHolder>(LocationDiffUtilCallback()) {


    class LocationDiffUtilCallback : DiffUtil.ItemCallback<MyLocation>() {

        override fun areItemsTheSame(oldItem: MyLocation, newItem: MyLocation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MyLocation, newItem: MyLocation): Boolean {
            return oldItem == newItem
        }
    }

    class LocationHolder(
        binding: ItemLocationBinding,
        onItemClick: (id: Long) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        private val locationTV = binding.location
        private val dateAndTimeTV = binding.dateAndTime
        private val imageViewAvatar = binding.avatarImageView
        private var id by Delegates.notNull<Long>()

        init {
            binding.root.setOnClickListener {
                onItemClick(id)
            }
        }

        private val formatDateTime = "HH:mm dd.MM.yyyy"
        private val formatter = DateTimeFormatter.ofPattern(formatDateTime)
            .withZone(ZoneId.systemDefault())

        fun bind(myLocation: MyLocation) {
            locationTV.text = myLocation.location
            dateAndTimeTV.text = formatter.format(myLocation.createdAt)
            id = myLocation.id

            Glide.with(itemView)
                .load(myLocation.avatarLink)
                .error(R.drawable.add_location)
                .into(imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationHolder {
        return LocationHolder(ItemLocationBinding.inflate(
            LayoutInflater.from(parent.context), parent, false), onItemClick)
    }

    override fun onBindViewHolder(holder: LocationHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun updateMyLocation(newItem: List<MyLocation>){
        submitList(newItem)
    }

}