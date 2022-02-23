package com.skillbox.phonebook.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skillbox.phonebook.R
import com.skillbox.phonebook.domain.Contact
import kotlin.properties.Delegates

class ContactsAdapter(
    val onContactClickListener: (id: Long) -> Unit
) : ListAdapter<Contact, ContactsAdapter.ContactViewHolder>(
    DiffUtilCallbackContact()
) {


    class DiffUtilCallbackContact : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }

    class ContactViewHolder(
        val view: View,
        onContactClickListener: (id: Long) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val nameContactVH: TextView = view.findViewById(R.id.nameItemTV)
        private val phoneNumberVH: TextView = view.findViewById(R.id.phoneNumberItemTV)
        private val icon: ImageView = view.findViewById(R.id.icPhoneItemImageView)
        private var currentId: Long? = null

        init {
            view.setOnClickListener {
                currentId?.let { currentId ->
                    onContactClickListener(currentId)
                }
            }
        }

        fun bind(contact: Contact, id: Long) {
            Glide.with(itemView)
                .load(R.drawable.ic_phone)
                .into(icon)

            nameContactVH.text = contact.name
            phoneNumberVH.text = contact.phoneNumber
            currentId = id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_contact,
            parent,
            false
        )
        return ContactViewHolder(view, onContactClickListener)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = getItem(position)
        holder.bind(contact, contact.id)
    }
}