package com.skillbox.phonebook.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.skillbox.phonebook.data.RepositoryImpl
import com.skillbox.phonebook.domain.Contact
import kotlinx.coroutines.launch

class ContactListViewModel(context: Application): AndroidViewModel(context) {
    private val repo = RepositoryImpl(context)

    private val _contacts = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>> = _contacts

    private val _contact = MutableLiveData<Int>()
    val contact: LiveData<Int> = _contact

    fun readContacts(){
        viewModelScope.launch {
            _contacts.postValue(repo.readContacts())
        }
    }

    fun deleteContact(id: Long){
        viewModelScope.launch {
            _contact.postValue(repo.deleteContact(id))
        }
    }

}