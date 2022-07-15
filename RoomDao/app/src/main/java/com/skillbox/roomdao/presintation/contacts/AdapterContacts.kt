package com.skillbox.roomdao.presintation.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.roomdao.data.entities.EContact
import com.skillbox.roomdao.databinding.ItemContactBinding

class AdapterContacts :
    ListAdapter<EContact, AdapterContacts.ViewHolderContacts>(DiffUtilContacts()) {

    class DiffUtilContacts : DiffUtil.ItemCallback<EContact>() {
        override fun areItemsTheSame(oldItem: EContact, newItem: EContact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EContact, newItem: EContact): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolderContacts(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(eContact: EContact) {
            binding.tvContactName.text = eContact.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderContacts {
        val binding =
            ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolderContacts(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderContacts, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}