package com.skillbox.messageapp.data.repositories

import android.app.Application
import com.skillbox.messageapp.data.db.MapperContact
import com.skillbox.messageapp.data.db.MessageDataBase
import com.skillbox.messageapp.domain.models.Contact
import com.skillbox.messageapp.domain.models.UsersAndContacts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactRepository(application: Application) {

    private val contactDao = MessageDataBase.getInstance(application).contactDao()
    private val userDao = MessageDataBase.getInstance(application).userDao()
    private val mapper = MapperContact()

    suspend fun addContact(contact: Contact) {
        withContext(Dispatchers.IO) {
            val entityContact = mapper.mapModelToEntityModel(contact)
            contactDao.addContact(entityContact)
        }
    }

    suspend fun getContacts(): List<Contact> {
        return withContext(Dispatchers.IO) {
            contactDao.getContacts().map {
                mapper.mapEntityModelToModel(it)
            }
        }
    }

    suspend fun getUsersAndContacts():List<UsersAndContacts>{
        return withContext(Dispatchers.IO){
            userDao.getUsersAndContactsEntity().map{
                mapper.mapEntityUsersAndEntityContacts(it)
            }
        }
    }
}