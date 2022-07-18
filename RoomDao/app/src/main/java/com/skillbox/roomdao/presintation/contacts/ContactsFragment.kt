package com.skillbox.roomdao.presintation.contacts

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.roomdao.R
import com.skillbox.roomdao.data.entities.EContact

class ContactsFragment : Fragment(R.layout.fragment_contacts) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterContacts: AdapterContacts
    private val viewModel by viewModels<ViewModelContacts>()
    private val listContact = mutableListOf<EContact>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initRV()
        initViewModel()
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.rvContacts)
    }

    private fun initRV() {
        adapterContacts = AdapterContacts()
        recyclerView.adapter = adapterContacts
        recyclerView.setHasFixedSize(true)
    }

    private fun initViewModel() {
        viewModel.getUserWithContacts()

        viewModel.contacts.observe(viewLifecycleOwner) { contacts ->
            adapterContacts.submitList(contacts)
            Log.d("contacts", "contactsFragment=${contacts}")
        }

        viewModel.getAllContacts()
    }

}