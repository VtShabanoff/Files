package com.skillbox.roomdao.data.dao

import androidx.room.*
import com.skillbox.roomdao.data.entities.ContactWithGroups
import com.skillbox.roomdao.data.entities.ContractContact
import com.skillbox.roomdao.data.entities.EContact

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addContact(eContact: EContact)

    @Delete
    suspend fun deleteContact(eContact: EContact)

    @Query("SELECT * FROM ${ContractContact.TABLE_NAME} WHERE ${ContractContact.Column.CONTACT_ID}=:id")
    suspend fun getContactById(id: Long): EContact

    @Query("SELECT * FROM ${ContractContact.TABLE_NAME}")
    suspend fun getAllContacts(): List<EContact>

    @Transaction
    @Query("SELECT * FROM ${ContractContact.TABLE_NAME}")
    suspend fun getContactsWithGroups(): List<ContactWithGroups>
}