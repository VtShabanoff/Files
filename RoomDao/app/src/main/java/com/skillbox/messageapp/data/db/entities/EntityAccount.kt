package com.skillbox.messageapp.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = AccountContract.TABLE_NAME)
data class EntityAccount(
    @PrimaryKey() @ColumnInfo(name = AccountContract.Columns.ID) val id: Long,
    @ColumnInfo(name = AccountContract.Columns.USER_ID) val userId: Long,
    @ColumnInfo(name = AccountContract.Columns.EMAIL) val email: String
)
