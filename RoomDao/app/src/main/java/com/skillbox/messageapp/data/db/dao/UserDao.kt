package com.skillbox.messageapp.data.db.dao

import androidx.room.*
import com.skillbox.messageapp.data.db.entities.*

@Dao
interface UserDao {

    @Query("SELECT * FROM ${UserContract.TABLE_NAME}")
    suspend fun getAllUsers(): List<EntityUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(entityUser: EntityUser)

    @Query("DELETE FROM ${UserContract.TABLE_NAME} WHERE user_id=:userId")
    suspend fun deleteUser(userId: Long)

    @Query("SELECT * FROM ${UserContract.TABLE_NAME} WHERE user_id=:userId")
    suspend fun getUserById(userId: Long): EntityUser?

    @Transaction
    @Query("SELECT * FROM ${UserContract.TABLE_NAME}")
    suspend fun getUsersAndAccounts(): List<EntityUserAndAccount>

    @Transaction
    @Query("SELECT * FROM ${UserContract.TABLE_NAME}") // table name = users
    suspend fun getUsersAndContactsEntity(): List<EntityUsersAndEntityContacts>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAccount(account: EntityAccount)
}