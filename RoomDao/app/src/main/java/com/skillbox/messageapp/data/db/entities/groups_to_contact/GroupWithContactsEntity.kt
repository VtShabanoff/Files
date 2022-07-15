package com.skillbox.messageapp.data.db.entities.groups_to_contact

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.skillbox.messageapp.data.db.entities.ContactContract
import com.skillbox.messageapp.data.db.entities.EntityContact

data class GroupWithContactsEntity(
    @Embedded() val groupEntity: GroupEntity,
    @Relation(
        parentColumn = GroupContract.Columns.ID,
        entityColumn = ContactContract.Columns.ID,
        associateBy = Junction(ContactGroupCrossRefEntity::class)
    )
    val contactsEntity: List<EntityContact>
)
