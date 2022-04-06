package com.skillbox.phonebook.domain

import android.database.Cursor
import android.provider.ContactsContract
import androidx.lifecycle.LiveData

interface RepositoryContacts {

    suspend fun readContacts(): List<Contact>

    suspend fun saveContact(name: String,familyName: String, phone: String, email: String)

    suspend fun saveEmail(id: Long, email: String)

    suspend fun savePhone(contactId: Long, phone: String)

    suspend fun deleteContact(id: Long): Int
}