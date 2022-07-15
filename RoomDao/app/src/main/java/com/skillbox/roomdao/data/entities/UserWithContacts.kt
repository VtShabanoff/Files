package com.skillbox.roomdao.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithContacts(
    @Embedded val eUser: EUser,
    @Relation(
        parentColumn = ContractUser.Columns.USER_ID, // здесь id юзера entity EUser
        entityColumn = ContractContact.Column.USER_ID // здесь id user entity EContact
    )
    val eContact: List<EContact>
)
