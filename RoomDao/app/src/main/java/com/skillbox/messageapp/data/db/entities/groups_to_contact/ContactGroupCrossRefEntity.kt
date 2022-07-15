package com.skillbox.messageapp.data.db.entities.groups_to_contact

import androidx.room.Entity
import com.skillbox.messageapp.data.db.entities.ContactContract
import com.skillbox.messageapp.data.db.entities.UserContract

@Entity(primaryKeys = [ContactContract.Columns.ID, GroupContract.Columns.ID])
data class ContactGroupCrossRefEntity(
    val contactId: Long,
    val groupId: Long
)
