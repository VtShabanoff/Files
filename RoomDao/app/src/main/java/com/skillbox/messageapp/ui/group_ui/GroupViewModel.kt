package com.skillbox.messageapp.ui.group_ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.skillbox.messageapp.data.repositories.GroupRepository
import com.skillbox.messageapp.domain.models.contact_with_group.Group
import kotlinx.coroutines.launch

class GroupViewModel(application: Application): AndroidViewModel(application) {

    private val repo = GroupRepository(application)

    private val _groups = MutableLiveData<List<Group>>()
    val groups: LiveData<List<Group>> = _groups

    fun addContact(group: Group){
        viewModelScope.launch {
            repo.addGroup(group)
        }
    }

    fun getGroups(){
        viewModelScope.launch {
            _groups.postValue(repo.getGroups())
        }
    }

    fun getGroupWithContacts(){
        viewModelScope.launch {
            repo.getGroupWithContacts()
        }
    }

    fun getContactWithGroups(){
        viewModelScope.launch {
            repo.getContactWithGroups()
        }
    }

}