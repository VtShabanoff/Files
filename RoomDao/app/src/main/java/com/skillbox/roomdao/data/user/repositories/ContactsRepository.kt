package com.skillbox.roomdao.data.user.repositories

import android.app.Application
import com.skillbox.roomdao.data.db.MessengerDataBase
import com.skillbox.roomdao.data.entities.EContact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactsRepository(context: Application) {

    companion object {
        private var INSTANCE: ContactsRepository? = null
        fun initialize(context: Application) {
            if (INSTANCE == null) INSTANCE = ContactsRepository(context)
        }

        fun getInstance(): ContactsRepository {
            return INSTANCE ?: throw IllegalStateException("ContactsRepository must be initialized")
        }
    }

    private val contactsDao = MessengerDataBase.getInstance(context).getContactDao()
    private val io = Dispatchers.IO

    init {
        for (i in 0 until 3) {
            CoroutineScope(io).launch {
                addContact(EContact(0L, "Contact$i", userId = 1))
            }
        }
    }

    suspend fun addContact(eContact: EContact) =
        withContext(io) {
            contactsDao.addContact(eContact)
        }

    suspend fun deleteContact(eContact: EContact) =
        withContext(io) {
            contactsDao.deleteContact(eContact)
        }

    suspend fun getContactById(id: Long): EContact =
        withContext(io) {
            contactsDao.getContactById(id)
        }

    suspend fun getAllContacts(): List<EContact> =
        withContext(io) {
            contactsDao.getAllContacts()
        }
}