package com.skillbox.roomdao.presintation.contacts_groups

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.roomdao.data.entities.EContact
import com.skillbox.roomdao.databinding.ItemContactBinding
import kotlin.properties.Delegates

class AdapterContacts(
    private val onItemClickContact: ((id: Long) -> Unit)
) :
    ListAdapter<EContact, AdapterContacts.ViewHolderContacts>(DiffUtilContacts()) {

    class DiffUtilContacts : DiffUtil.ItemCallback<EContact>() {
        override fun areItemsTheSame(oldItem: EContact, newItem: EContact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EContact, newItem: EContact): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolderContacts(
        private val binding: ItemContactBinding,
        onItemClickContact: (id: Long) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private var idContact by Delegates.notNull<Long>()

        init {
            binding.root.setOnClickListener {
                onItemClickContact(idContact)
            }
        }

        fun bind(eContact: EContact) {
            idContact = eContact.id
            binding.tvContactName.text = eContact.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderContacts {
        val binding =
            ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolderContacts(binding, onItemClickContact)
    }

    override fun onBindViewHolder(holder: ViewHolderContacts, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}