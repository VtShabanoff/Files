package com.skillbox.roomdao.data.user.repositories

import android.app.Application
import com.skillbox.roomdao.data.db.MessengerDataBase
import com.skillbox.roomdao.data.entities.EEmail
import com.skillbox.roomdao.data.entities.EUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(context: Application) {

    private val userDao = MessengerDataBase.getInstance(context).getUserDao()

    suspend fun getUser(id: Long): EUser =
        withContext(Dispatchers.IO) {
            userDao.getUser(id)
        }

    suspend fun addUser(eUser: EUser) =
        withContext(Dispatchers.IO) {
            userDao.addUser(eUser)
        }

    suspend fun addEmail(eEmail: EEmail) =
        withContext(Dispatchers.IO) {
            userDao.addEmail(eEmail)
        }

    suspend fun getListUserWithEmail() =
        withContext(Dispatchers.IO) {
            userDao.getUsersWithComponents()
        }

    suspend fun getListUserWithContacts() =
        withContext(Dispatchers.IO) {
            userDao.getUserWithContacts()
        }
}