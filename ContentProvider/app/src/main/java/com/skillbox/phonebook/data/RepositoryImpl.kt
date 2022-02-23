package com.skillbox.phonebook.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skillbox.phonebook.domain.Contact
import com.skillbox.phonebook.domain.RepositoryContacts

object RepositoryImpl : RepositoryContacts {

    private val contactList = mutableListOf<Contact>()
    private val _contacts = MutableLiveData<List<Contact>>()

    init {
        for (i in 0 until 10){
            val item = Contact("name $i", "89005557744")
            addContact(item)
        }
    }

    override fun addContact(contact: Contact) {
        contactList.add(contact)
        updateList()
    }

    override fun editContact(contact: Contact) {
        val oldContact = getContactFromId(contact.id)
        contactList.remove(oldContact)
        addContact(contact)
    }

    override fun deleteContact(contact: Contact) {
        contactList.remove(contact)
        updateList()
    }

    override fun getContactFromId(idContact: Long): Contact {
        return contactList.find { contact ->
            contact.id == idContact
        } ?: throw Exception("no found contact in contact list")
    }

    override fun getContacts(): LiveData<List<Contact>> {
        return _contacts
    }

    private fun updateList() {
        _contacts.postValue(contactList.toList())
    }
}