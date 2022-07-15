package com.skillbox.messageapp.ui.contact_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.messageapp.databinding.ItemContactBinding
import com.skillbox.messageapp.domain.models.Contact

class ContactListAdapter : ListAdapter<Contact, ContactListAdapter.ContactViewHolder>(
    DiffUtilContact()
) {
    class DiffUtilContact : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }

    class ContactViewHolder(private val binding: ItemContactBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(contact: Contact) {
            binding.nameContactTv.text = contact.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}