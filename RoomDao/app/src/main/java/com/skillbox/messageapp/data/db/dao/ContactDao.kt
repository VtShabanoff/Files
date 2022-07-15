package com.skillbox.messageapp.data.db.dao

import androidx.room.*
import com.skillbox.messageapp.data.db.entities.*
import com.skillbox.messageapp.data.db.entities.groups_to_contact.ContactWithGroupsEntity

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addContact(entityContact: EntityContact)

    @Query("SELECT * FROM ${ContactContract.TABLE_NAME}")
    suspend fun getContacts(): List<EntityContact>

}