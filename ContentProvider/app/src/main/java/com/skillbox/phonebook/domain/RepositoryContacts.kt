package com.skillbox.phonebook.domain

import androidx.lifecycle.LiveData

interface RepositoryContacts {

    fun addContact(contact: Contact)

    fun editContact(contact: Contact)

    fun deleteContact(contact: Contact)

    fun getContactFromId(idContact: Long): Contact

    fun getContacts(): LiveData<List<Contact>>
}