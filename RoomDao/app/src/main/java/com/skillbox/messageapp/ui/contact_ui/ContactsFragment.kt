package com.skillbox.messageapp.ui.contact_ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.messageapp.R
import com.skillbox.messageapp.databinding.FragmentContactsBinding
import com.skillbox.messageapp.domain.models.Contact
import com.skillbox.messageapp.ui.ViewBindingFragment
import com.skillbox.messageapp.ui.user_ui.UserListAdapter

class ContactsFragment: Fragment(R.layout.fragment_contacts){
    private val binding: FragmentContactsBinding by viewBinding()
    private val viewModel: ContactsViewModel by viewModels()
    private lateinit var adapter: ContactListAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        viewModel.addContact(Contact(id = 1, "User1"))
        viewModel.addContact(Contact(id = 2, "User2"))
        viewModel.getUsersAndContacts()
        viewModel.contacts.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        viewModel.getContacts()
    }
    private fun initRV(){
        adapter = ContactListAdapter()
        binding.contactsRv.adapter = adapter
        binding.contactsRv.setHasFixedSize(true)
    }
}