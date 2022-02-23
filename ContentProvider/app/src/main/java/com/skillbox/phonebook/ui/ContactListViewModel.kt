package com.skillbox.phonebook.ui

import androidx.lifecycle.ViewModel
import com.skillbox.phonebook.data.RepositoryImpl
import com.skillbox.phonebook.domain.Contact

class ContactListViewModel: ViewModel() {
    private val repo = RepositoryImpl

    fun addContact(contact: Contact){
        repo.addContact(contact)
    }

    fun deleteContact(contact: Contact){
        repo.deleteContact(contact)
    }

    fun editContact(contact: Contact){
        repo.editContact(contact)
    }

    val contactsLD = repo.getContacts()

}