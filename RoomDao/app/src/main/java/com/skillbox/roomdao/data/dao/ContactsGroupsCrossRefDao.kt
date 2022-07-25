package com.skillbox.roomdao.data.dao

import androidx.room.*
import com.skillbox.roomdao.data.entities.*

@Dao
interface ContactsGroupsCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCrossRefContactsWithGroups(crossRef: ContactGroupCrossRef)

    @Transaction
    @Query("SELECT * FROM ${ContractContact.TABLE_NAME} WHERE ${ContractContact.Column.CONTACT_ID}=:id")
    suspend fun getContactWithGroupsById(id: Long): ContactWithGroups

    @Transaction
    @Query("SELECT * FROM ${ContractGroup.TABLE_NAME} WHERE ${ContractGroup.Columns.GROUP_ID}=:id")
    suspend fun getGroupWithContactsById(id: Long): GroupWithContacts
}