package com.skillbox.phonebook.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.phonebook.R
import com.skillbox.phonebook.databinding.FragmentContactsBinding

class ContactsFragment : Fragment(R.layout.fragment_contacts) {
    private val binding by viewBinding(FragmentContactsBinding::class.java)
    private val viewModel: ContactListViewModel by viewModels()
    private lateinit var adapterContacts: ContactsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.contactsLD.observe(viewLifecycleOwner) {
            adapterContacts.submitList(it)
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        with(binding.contactsRv) {
            adapterContacts = ContactsAdapter(){
                findNavController()
                    .navigate(R.id.action_contactsFragment_to_detailedInfoContactFragment)
            }
            adapter = adapterContacts
        }

    }
}