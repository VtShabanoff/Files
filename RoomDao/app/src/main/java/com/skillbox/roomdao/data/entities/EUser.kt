package com.skillbox.roomdao.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ContractUser.TABLE_NAME)
data class EUser(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ContractUser.Columns.USER_ID)
    val id: Long,
    @ColumnInfo(name = ContractUser.Columns.USER_NAME)
    val name: String
)