package com.skillbox.roomdao.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ContractGroup.TABLE_NAME)
data class EGroup(
    @PrimaryKey
    @ColumnInfo(name = ContractGroup.Columns.GROUP_ID)
    val id: Long,
    @ColumnInfo(name = ContractGroup.Columns.GROUP_NAME)
    val name: String
)
