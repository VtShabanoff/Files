package com.skillbox.phonebook.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.skillbox.phonebook.data.RepositoryImpl
import com.skillbox.phonebook.domain.Contact
import kotlinx.coroutines.launch

class UserInfoViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = RepositoryImpl(app)

    private val _phoneNumbers = MutableLiveData<List<String>>()
    val phoneNumbers: LiveData<List<String>> = _phoneNumbers

    private val _emails = MutableLiveData<List<String>>()
    val emails: LiveData<List<String>> = _emails

    private val _contact = MutableLiveData<Int>()
    val contact: LiveData<Int> = _contact

    fun savePhones(id: Long) {
        viewModelScope.launch {
            val phones = repo.getPhonesFromContacts(id)
            _phoneNumbers.postValue(phones)
        }
    }

    fun saveEmails(id: Long) {
        viewModelScope.launch {
            val emails = repo.getEmailsFromContacts(id)
            _emails.postValue(emails)
        }
    }

    fun deleteContact(id: Long){
       viewModelScope.launch {
           _contact.postValue(repo.deleteContact(id))
        }
    }
}