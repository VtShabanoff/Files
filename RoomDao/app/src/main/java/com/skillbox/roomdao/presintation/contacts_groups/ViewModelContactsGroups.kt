package com.skillbox.roomdao.presintation.contacts_groups

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.skillbox.roomdao.SingleLiveEvent
import com.skillbox.roomdao.data.entities.ContactGroupCrossRef
import com.skillbox.roomdao.data.entities.ContactWithGroups
import com.skillbox.roomdao.data.entities.EContact
import com.skillbox.roomdao.data.entities.EGroup
import com.skillbox.roomdao.data.user.repositories.ContactsGroupsRepository
import com.skillbox.roomdao.data.user.repositories.UserRepository
import kotlinx.coroutines.launch

class ViewModelContactsGroups(context: Application) : AndroidViewModel(context) {

    private val repositoryContactsGroups = ContactsGroupsRepository.getInstance()
    private val repositoryUser = UserRepository(context)

    private val _contacts =
        SingleLiveEvent<List<EContact>>() // создал чтобы проверить поведение списка контактов
    val contacts: SingleLiveEvent<List<EContact>> = _contacts

    private val _groups = MutableLiveData<List<EGroup>>()
    val groups: LiveData<List<EGroup>> = _groups

    private val _groupsByContacts = MutableLiveData<ContactWithGroups>()
    val groupsByContacts: LiveData<ContactWithGroups> = _groupsByContacts

    init {
        getAllContacts()
        getUserWithContacts()
        getAllGroups()
        getContactsWithGroups()
        getGroupsWithContacts()
    }

    fun addContact(eContact: EContact) {
        viewModelScope.launch {
            repositoryContactsGroups.addContact(eContact)
        }
    }

    fun deleteContact(eContact: EContact) {
        viewModelScope.launch {
            repositoryContactsGroups.deleteContact(eContact)
        }
    }

    fun getContactById(id: Long){
        viewModelScope.launch {
            repositoryContactsGroups.getContactById(id)
        }
    }

    private fun getAllContacts() {
        viewModelScope.launch {
            _contacts.postValue(repositoryContactsGroups.getAllContacts())
        }
    }

    private fun getUserWithContacts() {
        viewModelScope.launch {
            repositoryUser.getListUserWithContacts()
        }
    }

    private fun getAllGroups() {
        viewModelScope.launch {
            _groups.postValue(repositoryContactsGroups.getAllGroups())
        }
    }

    fun addGroup(eGroup: EGroup) {
        viewModelScope.launch {
            repositoryContactsGroups.addGroup(eGroup)
        }
    }

    fun getGroupById(id: Long) {
        viewModelScope.launch {
            repositoryContactsGroups.getGroupById(id)
        }
    }

    private fun getContactsWithGroups() {
        viewModelScope.launch {
            repositoryContactsGroups.getContactWithGroups()
        }
    }

    private fun getGroupsWithContacts() {
        viewModelScope.launch {
            repositoryContactsGroups.getGroupWithContacts()
        }
    }

    fun addCrossRefContactsGroups(crossRef: ContactGroupCrossRef) {
        viewModelScope.launch {
            repositoryContactsGroups.addCrossRefContactsGroups(crossRef)
        }
    }

    fun getContactWithGroupsById(id: Long) {
        viewModelScope.launch {
            _groups.postValue(repositoryContactsGroups.getGroupsWithContactById(id).groups)

        }
    }

    fun getGroupWithContactsById(id: Long) {
        viewModelScope.launch {
            _contacts.postValue(repositoryContactsGroups.getContactsWithGroupsById(id).contacts)
        }
    }
}