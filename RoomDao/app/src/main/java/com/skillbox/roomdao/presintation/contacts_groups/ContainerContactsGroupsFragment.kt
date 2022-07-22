package com.skillbox.roomdao.presintation.contacts_groups

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.roomdao.R
import com.skillbox.roomdao.data.entities.ContactGroupCrossRef
import com.skillbox.roomdao.data.entities.EContact
import com.skillbox.roomdao.data.entities.EGroup

class ContainerContactsGroupsFragment : Fragment(R.layout.fragment_contacts_groups) {
    private lateinit var rvContacts: RecyclerView
    private lateinit var rvGroups: RecyclerView
    private lateinit var adapterContacts: AdapterContacts
    private lateinit var adapterGroups: AdapterGroups
    private val viewModel by viewModels<ViewModelContactsGroups>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        addContacts()
        addContactsGroupsCrossRef()
        addGroups()
        initRV()
        observe()
    }

    private fun initViews(view: View) {
        rvContacts = view.findViewById(R.id.rvContacts)
        rvGroups = view.findViewById(R.id.rvGroups)
    }

    private fun initRV() {
        adapterContacts = AdapterContacts { id ->
            viewModel.getContactWithItsGroupsByIdCrossRef(id)
        }
        adapterGroups = AdapterGroups{
            toast("on click group id $it")
        }
        rvContacts.adapter = adapterContacts
        rvGroups.adapter = adapterGroups
        rvContacts.setHasFixedSize(true)
        rvGroups.setHasFixedSize(true)
    }

    private fun observe() {
        viewModel.contacts.observe(viewLifecycleOwner) { contacts ->
            adapterContacts.submitList(contacts)
            Log.d("contacts", "contactsFragment=${contacts}")
        }

        viewModel.groups.observe(viewLifecycleOwner){
            adapterGroups.submitList(it)
            Log.d("contacts", "contactsFragment=${it}")
        }
    }

    private fun addContacts(){
        viewModel.addContact(EContact(1, "Contact1", userId = 1))
        viewModel.addContact(EContact(2, "Contact2", userId = 1))
        viewModel.addContact(EContact(3, "Contact3", userId = 1))
    }

    private fun addGroups(){
        viewModel.addGroup(EGroup(1, "Group1"))
        viewModel.addGroup(EGroup(2, "Group2"))
        viewModel.addGroup(EGroup(3, "Group3"))
        viewModel.addGroup(EGroup(4, "Group4"))
        viewModel.addGroup(EGroup(5, "Group5"))
        viewModel.addGroup(EGroup(6, "Group6"))
        viewModel.addGroup(EGroup(7, "Group7"))
        viewModel.addGroup(EGroup(8, "Group8"))
        viewModel.addGroup(EGroup(9, "Group9"))
    }

    private fun addContactsGroupsCrossRef(){
        viewModel.addCrossRefContactsGroups(ContactGroupCrossRef(1, 1))
        viewModel.addCrossRefContactsGroups(ContactGroupCrossRef(1, 2))
        viewModel.addCrossRefContactsGroups(ContactGroupCrossRef(1, 3))
        viewModel.addCrossRefContactsGroups(ContactGroupCrossRef(1, 8))
        viewModel.addCrossRefContactsGroups(ContactGroupCrossRef(1, 5))

        viewModel.addCrossRefContactsGroups(ContactGroupCrossRef(2, 1))
        viewModel.addCrossRefContactsGroups(ContactGroupCrossRef(2, 8))
        viewModel.addCrossRefContactsGroups(ContactGroupCrossRef(2, 9))
        viewModel.addCrossRefContactsGroups(ContactGroupCrossRef(2, 7))
        viewModel.addCrossRefContactsGroups(ContactGroupCrossRef(2, 6))

        viewModel.addCrossRefContactsGroups(ContactGroupCrossRef(3, 4))
        viewModel.addCrossRefContactsGroups(ContactGroupCrossRef(3, 5))
        viewModel.addCrossRefContactsGroups(ContactGroupCrossRef(3, 6))
        viewModel.addCrossRefContactsGroups(ContactGroupCrossRef(3, 7))
        viewModel.addCrossRefContactsGroups(ContactGroupCrossRef(3, 2))
    }

    val toast = fun(message: String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}