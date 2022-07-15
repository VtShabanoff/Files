package com.skillbox.messageapp.data.db.dao

import androidx.room.Query
import com.skillbox.messageapp.data.db.entities.ContactContract
import com.skillbox.messageapp.data.db.entities.groups_to_contact.ContactGroupCrossRefEntity
import com.skillbox.messageapp.data.db.entities.groups_to_contact.ContactWithGroupsEntity

interface ContactGroupDao {
    @Query("SELECT * FROM ${ContactContract.TABLE_NAME}")
    suspend fun getContactGroupCrossRef(): List<ContactGroupCrossRefEntity>
}