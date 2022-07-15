package com.skillbox.messageapp.data.db.entities.groups_to_contact

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = GroupContract.TABLE_NAME)
data class GroupEntity(
    @PrimaryKey
    @ColumnInfo(name = GroupContract.Columns.ID)
    val groupId: Long,
    @ColumnInfo(name = GroupContract.Columns.NAME)
    val name: String
)
