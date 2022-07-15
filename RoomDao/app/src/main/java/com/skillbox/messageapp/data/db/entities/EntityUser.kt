package com.skillbox.messageapp.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserContract.TABLE_NAME)
data class EntityUser(
    @PrimaryKey() @ColumnInfo(UserContract.Columns.ID) val id: Long,
    @ColumnInfo(name = UserContract.Columns.FIRST_NAME) val firstName: String,
    @ColumnInfo(name = UserContract.Columns.LAST_NAME) val lastName: String?,
    @ColumnInfo(name = UserContract.Columns.AVATAR) val avatar: String?
)
