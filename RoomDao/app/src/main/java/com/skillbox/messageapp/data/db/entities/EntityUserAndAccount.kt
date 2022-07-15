package com.skillbox.messageapp.data.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class EntityUserAndAccount(
    @Embedded val entityUser: EntityUser,
    @Relation(
        parentColumn = UserContract.Columns.ID,
        entityColumn = AccountContract.Columns.USER_ID
    )
    val entityAccount: EntityAccount
)