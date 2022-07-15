package com.skillbox.roomdao.data.dao

import androidx.room.*
import com.skillbox.roomdao.data.entities.*

@Dao
interface DaoUser{
    @Query("SELECT * FROM ${ContractUser.TABLE_NAME} WHERE user_id=:id")
    suspend fun getUser(id: Long): EUser

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(eUser: EUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEmail(eEmail: EEmail)

    @Transaction
    @Query("SELECT * FROM ${ContractUser.TABLE_NAME}")
    suspend fun getUsersWithComponents(): List<EUserWithComponent>

    @Transaction
    @Query("SELECT * FROM ${ContractUser.TABLE_NAME}")
    suspend fun getUserWithContacts(): List<UserWithContacts>
}