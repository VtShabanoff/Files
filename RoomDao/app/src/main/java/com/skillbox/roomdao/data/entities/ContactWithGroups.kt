package com.skillbox.roomdao.data.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class ContactWithGroups(
    @Embedded val contact: EContact,
    @Relation(
        parentColumn = ContractContact.Column.CONTACT_ID,
        entityColumn = ContractGroup.Columns.GROUP_ID,
        associateBy = Junction(ContactGroupCrossRef::class)
    )
    val groups: List<EGroup>
)
