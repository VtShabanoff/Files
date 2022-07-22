package com.skillbox.roomdao.data.entities

import androidx.room.Entity

@Entity(primaryKeys = [ContractContact.Column.CONTACT_ID, ContractGroup.Columns.GROUP_ID])
data class ContactGroupCrossRef(
    val contact_id: Long,
    val id_group: Long
)
