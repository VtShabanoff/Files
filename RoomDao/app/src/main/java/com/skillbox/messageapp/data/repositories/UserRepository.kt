package com.skillbox.messageapp.data.repositories

import android.app.Application
import com.skillbox.messageapp.data.db.MessageDataBase
import com.skillbox.messageapp.data.db.entities.EntityAccount
import com.skillbox.messageapp.data.db.entities.EntityUser
import com.skillbox.messageapp.data.db.entities.EntityUserAndAccount
import com.skillbox.messageapp.domain.models.Account
import com.skillbox.messageapp.domain.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(application: Application){

    private val userDao = MessageDataBase.getInstance(application).userDao()

    suspend fun getAllUsers(): List<EntityUser>{
       return withContext(Dispatchers.IO){
             userDao.getAllUsers()
        }
    }

    suspend fun addUser(user: User){
        withContext(Dispatchers.IO){
            val entityUser = EntityUser(
                id = 1,
                firstName = user.firstName,
                lastName = user.lastName,
                avatar = user.avatar
                )
            userDao.addUser(entityUser)
        }
    }

    suspend fun deleteUser(id: Long){
        withContext(Dispatchers.IO){
            userDao.deleteUser(id)
        }
    }

    suspend fun getUserFromId(userId: Long): EntityUser?{
        return withContext(Dispatchers.IO){
            userDao.getUserById(userId)
        }
    }

    suspend fun getUsersAndAccounts(): List<EntityUserAndAccount>{
        return withContext(Dispatchers.IO){
            userDao.getUsersAndAccounts()
        }
    }

    suspend fun addAccount(account: Account){
        withContext(Dispatchers.IO){
            val entityAccount = EntityAccount(
                id = account.id,
                userId = 1,
                email = account.email
            )
            userDao.addAccount(entityAccount)
        }
    }
}