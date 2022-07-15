package com.skillbox.messageapp.ui.contact_ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.skillbox.messageapp.data.repositories.ContactRepository
import com.skillbox.messageapp.domain.models.Contact
import com.skillbox.messageapp.domain.models.UsersAndContacts
import kotlinx.coroutines.launch

class ContactsViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = ContactRepository(application)

    private val _contacts = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>> = _contacts

    fun addContact(contact: Contact) {
        viewModelScope.launch {
            repo.addContact(contact)
        }
    }

    fun getContacts(){
        viewModelScope.launch {
            _contacts.postValue(repo.getContacts())
        }
    }

    fun getUsersAndContacts(){
        viewModelScope.launch {
            repo.getUsersAndContacts()
        }
    }
}