package com.skillbox.roomdao.data.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class GroupWithContacts(
    @Embedded() val group: EGroup,
    @Relation(
        parentColumn = ContractGroup.Columns.GROUP_ID,
        entityColumn = ContractContact.Column.CONTACT_ID,
        associateBy = Junction(ContactGroupCrossRef::class)
    )
    val contacts: List<EContact>
)
