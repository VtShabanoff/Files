package com.skillbox.messageapp.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ContactContract.TABLE_NAME)
data class EntityContact(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = ContactContract.Columns.ID) val id: Long,
    @ColumnInfo(name = ContactContract.Columns.NAME) val name: String
){
    @ColumnInfo(name = ContactContract.Columns.USER_ID) val userId: Long = 0
}
