package com.skillbox.messageapp.ui.group_ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.messageapp.databinding.ItemGroupBinding
import com.skillbox.messageapp.domain.models.contact_with_group.Group

class GroupListAdapter: ListAdapter<Group, GroupListAdapter.GroupViewHolder>(DiffUtilGroup()) {

    class DiffUtilGroup: DiffUtil.ItemCallback<Group>(){
        override fun areItemsTheSame(oldItem: Group, newItem: Group): Boolean {
            return oldItem.groupId == newItem.groupId
        }

        override fun areContentsTheSame(oldItem: Group, newItem: Group): Boolean {
            return oldItem == newItem
        }
    }

    class GroupViewHolder(
        private val binding: ItemGroupBinding
    ) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}