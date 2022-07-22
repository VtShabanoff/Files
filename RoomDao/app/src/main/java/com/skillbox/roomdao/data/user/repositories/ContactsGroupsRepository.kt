package com.skillbox.roomdao.data.user.repositories

import android.app.Application
import android.util.Log
import com.skillbox.roomdao.data.db.MessengerDataBase
import com.skillbox.roomdao.data.entities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactsGroupsRepository(context: Application) {

    companion object {
        private var INSTANCE: ContactsGroupsRepository? = null
        fun initialize(context: Application) {
            if (INSTANCE == null) INSTANCE = ContactsGroupsRepository(context)
        }

        fun getInstance(): ContactsGroupsRepository {
            return INSTANCE ?: throw IllegalStateException("ContactsRepository must be initialized")
        }
    }

    private val contactsDao = MessengerDataBase.getInstance(context).getContactDao()
    private val groupDao = MessengerDataBase.getInstance(context).getGroupDao()
    private val crossRefDao = MessengerDataBase.getInstance(context).getContactsGroupsCrossRef()
    private val io = Dispatchers.IO

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

    suspend fun getAllGroups(): List<EGroup> =
        withContext(io) {
            groupDao.getAllGroups()
        }

    suspend fun addGroup(eGroup: EGroup) =
        withContext(io) {
            groupDao.addGroup(eGroup)
        }

    suspend fun getGroupById(id: Long): EGroup =
        withContext(io) {
            groupDao.getGroupById(id)
        }

    suspend fun getContactWithGroups() {
        withContext(io) {
            contactsDao.getContactsWithGroups()
        }
    }

    suspend fun getGroupWithContacts() {
        withContext(io) {
            groupDao.getGroupsWithContacts()
        }
    }

    suspend fun addCrossRefContactsGroups(crossRef: ContactGroupCrossRef) {
        withContext(io) {
            crossRefDao.addCrossRefContactsWithGroups(crossRef)
        }
    }

    suspend fun getContactsWithGroupsByIdCrossRef(id: Long) {
        withContext(io) {
            crossRefDao.getContactWithGroupsById(id)
        }
    }

    suspend fun getContactWithItsGroupsByIdCrossRef(id: Long): ContactWithGroups {
        Log.d("ContactAndGroups",
            "contact name = ${crossRefDao.getContactWithGroupsById(id)}")
        return withContext(io) {
            crossRefDao.getContactWithGroupsById(id)

        }
    }


    suspend fun getGroupWithItsContactsByIdCrossRef(id: Long): GroupWithContacts =
        withContext(io) {
            crossRefDao.getGroupWithContactsById(id)
        }
}