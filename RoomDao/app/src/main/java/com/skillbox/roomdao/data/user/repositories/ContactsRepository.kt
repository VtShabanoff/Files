package com.skillbox.roomdao.data.user.repositories

import android.app.Application
import com.skillbox.roomdao.data.db.MessengerDataBase
import com.skillbox.roomdao.data.entities.EContact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactsRepository(context: Application) {

    private val contactsDao = MessengerDataBase.getInstance(context).getContactDao()
    private val io = Dispatchers.IO

    suspend fun addContact(eContact: EContact) =
        withContext(io){
            contactsDao.addContact(eContact)
        }

    suspend fun deleteContact(eContact: EContact) =
        withContext(io){
            contactsDao.deleteContact(eContact)
        }

    suspend fun getContactById(id: Long): EContact =
        withContext(io){
            contactsDao.getContactById(id)
        }

    suspend fun getAllContacts(): List<EContact> =
        withContext(io){
            contactsDao.getAllContacts()
        }
}