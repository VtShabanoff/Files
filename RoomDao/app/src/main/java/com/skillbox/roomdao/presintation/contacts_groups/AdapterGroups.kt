package com.skillbox.roomdao.presintation.contacts_groups

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.roomdao.data.entities.EGroup
import com.skillbox.roomdao.databinding.ItemGroupBinding
import kotlin.properties.Delegates

class AdapterGroups(private val onClickItemGroup: ((id: Long) -> Unit)) :
    ListAdapter<EGroup, AdapterGroups.ViewHolderGroups>(DiffUtilGroups()) {

    class DiffUtilGroups : DiffUtil.ItemCallback<EGroup>() {
        override fun areItemsTheSame(oldItem: EGroup, newItem: EGroup): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EGroup, newItem: EGroup): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolderGroups(
        private val binding: ItemGroupBinding,
        onClickItemGroup: (id: Long) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private var idItemGroup by Delegates.notNull<Long>()

        init {
            binding.root.setOnClickListener {
                onClickItemGroup(idItemGroup)
            }
        }
        fun bind(eGroup: EGroup) {
            idItemGroup = eGroup.id
            binding.tvGroupName.text = eGroup.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderGroups {
        val binding =
            ItemGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolderGroups(binding, onClickItemGroup)
    }

    override fun onBindViewHolder(holder: ViewHolderGroups, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}