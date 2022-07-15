package com.skillbox.messageapp.data.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class EntityUsersAndEntityContacts(
    @Embedded val entityUser: EntityUser,
    @Relation(
        parentColumn = UserContract.Columns.ID,
        entityColumn = ContactContract.Columns.USER_ID
    )
    val entityContacts: List<EntityContact>
)
