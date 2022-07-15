package com.skillbox.roomdao.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class EUserWithComponent(
    @Embedded val eUser: EUser,
    @Relation(
        parentColumn = ContractUser.Columns.USER_ID,
        entityColumn = ContractEmail.Columns.EMAIL_ID
    )
    val eEmail: EEmail
)