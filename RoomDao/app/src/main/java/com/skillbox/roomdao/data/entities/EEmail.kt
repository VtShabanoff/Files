package com.skillbox.roomdao.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ContractEmail.TABLE_NAME)
data class EEmail(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ContractEmail.Columns.EMAIL_ID)
    val id: Long,
    @ColumnInfo(name = ContractEmail.Columns.EMAIL)
    val email: String
)