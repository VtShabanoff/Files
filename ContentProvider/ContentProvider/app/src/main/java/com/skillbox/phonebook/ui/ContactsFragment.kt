package com.skillbox.phonebook.ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.phonebook.R
import com.skillbox.phonebook.databinding.FragmentContactsBinding
import com.skillbox.phonebook.domain.Contact

class ContactsFragment : Fragment(R.layout.fragment_contacts) {
    private val binding by viewBinding(FragmentContactsBinding::class.java)
    private val viewModel: ContactListViewModel by viewModels()
    private lateinit var adapterContacts: ContactsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val permissionGranted = ActivityCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED

        if (permissionGranted) {
            requestContacts()
        } else {
            requestPermission()
        }
        setupRecyclerView()
        addContact()
        observerUpdateContacts()
    }

    private fun requestContacts() {
        viewModel.readContacts()
    }

    private fun observerUpdateContacts() {
        viewModel.contacts.observe(viewLifecycleOwner) { contacts ->
            adapterContacts.submitList(contacts)
            Log.d("contacts", "contacts = $contacts")
        }
    }

    private fun setupRecyclerView() {
        with(binding.contactsRv) {
            adapterContacts = ContactsAdapter {
                findNavController()
                    .navigate(
                        R.id.action_contactsFragment_to_userInfoFragment,
                        bundleOf(
                            AddContactFragment.CONTACT_ITEM_ID to it.id,
                            NAME to it.name
                        )
                    )
            }
            adapter = adapterContacts
        }
    }

    private fun addContact() {
        binding.buttonAddContact.setOnClickListener {
            findNavController()
                .navigate(R.id.action_contactsFragment_to_detailedInfoContactFragment)
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                android.Manifest.permission.READ_CONTACTS
            ),
            RED_CONTACTS_RC
        )
    }

    companion object {
        const val RED_CONTACTS_RC = 100
        const val NAME = "name"
    }
}