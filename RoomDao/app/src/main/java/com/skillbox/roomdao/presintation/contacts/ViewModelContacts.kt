package com.skillbox.roomdao.presintation.contacts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.skillbox.roomdao.SingleLiveEvent
import com.skillbox.roomdao.data.entities.EContact
import com.skillbox.roomdao.data.entities.UserWithContacts
import com.skillbox.roomdao.data.user.repositories.ContactsRepository
import com.skillbox.roomdao.data.user.repositories.UserRepository
import kotlinx.coroutines.launch

class ViewModelContacts(context: Application): AndroidViewModel(context) {

    private val repositoryContacts = ContactsRepository(context)
    private val repositoryUser = UserRepository(context)

    private val _contacts = SingleLiveEvent<List<EContact>>() // создал чтобы проверить поведение списка контактов
    val contacts: SingleLiveEvent<List<EContact>> = _contacts

    fun addContact(eContact: EContact){
        viewModelScope.launch {
            repositoryContacts.addContact(eContact)
        }
    }

    fun deleteContact(eContact: EContact){
        viewModelScope.launch {
            repositoryContacts.deleteContact(eContact)
        }
    }

    fun getContactById(id: Long){
        viewModelScope.launch {
            repositoryContacts.getContactById(id)
        }
    }

    fun getAllContacts(){
        viewModelScope.launch {
            _contacts.postValue(repositoryContacts.getAllContacts())
        }
    }

    fun getUserWithContacts(){
        viewModelScope.launch {
            repositoryUser.getListUserWithContacts()
        }
    }
}