package com.skillbox.roomdao.data.dao

import androidx.room.*
import com.skillbox.roomdao.data.entities.ContractGroup
import com.skillbox.roomdao.data.entities.EGroup
import com.skillbox.roomdao.data.entities.GroupWithContacts

@Dao
interface GroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGroup(eGroup: EGroup)

    @Delete
    suspend fun deleteGroup(eGroup: EGroup)

    @Query("SELECT * FROM ${ContractGroup.TABLE_NAME} WHERE ${ContractGroup.Columns.GROUP_ID}=:id")
    suspend fun getGroupById(id: Long): EGroup

    @Query("SELECT * FROM ${ContractGroup.TABLE_NAME}")
    suspend fun getAllGroups(): List<EGroup>

    @Transaction
    @Query("SELECT * FROM ${ContractGroup.TABLE_NAME}")
    suspend fun getGroupsWithContacts(): List<GroupWithContacts>
}