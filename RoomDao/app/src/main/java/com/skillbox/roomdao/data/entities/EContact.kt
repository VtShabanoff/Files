package com.skillbox.roomdao.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ContractContact.TABLE_NAME)
data class EContact(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ContractContact.Column.CONTACT_ID)
    val id: Long,
    @ColumnInfo(name = ContractContact.Column.CONTACT_NAME)
    val name: String,
    @ColumnInfo(name = ContractContact.Column.USER_ID)
    val userId: Long
)