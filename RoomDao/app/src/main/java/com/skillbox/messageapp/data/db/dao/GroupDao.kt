package com.skillbox.messageapp.data.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.skillbox.messageapp.data.db.entities.groups_to_contact.ContactWithGroupsEntity
import com.skillbox.messageapp.data.db.entities.groups_to_contact.GroupContract
import com.skillbox.messageapp.data.db.entities.groups_to_contact.GroupEntity
import com.skillbox.messageapp.data.db.entities.groups_to_contact.GroupWithContactsEntity

interface GroupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGroup(groupEntity: GroupEntity)

    @Query("SELECT * FROM ${GroupContract.TABLE_NAME}")
    suspend fun getGroups(): List<GroupEntity>

    @Transaction
    @Query("SELECT * FROM ${GroupContract.TABLE_NAME}")
    suspend fun getGroupWithContactsEntity(): List<GroupWithContactsEntity>

    @Transaction
    @Query("SELECT * FROM ${GroupContract.TABLE_NAME}")
    suspend fun getContactWithGroupsEntity(): List<ContactWithGroupsEntity>
}