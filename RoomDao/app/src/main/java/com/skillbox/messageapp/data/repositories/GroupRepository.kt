package com.skillbox.messageapp.data.repositories

import android.app.Application
import com.skillbox.messageapp.data.db.MapperGroup
import com.skillbox.messageapp.data.db.MessageDataBase
import com.skillbox.messageapp.data.db.entities.groups_to_contact.ContactWithGroupsEntity
import com.skillbox.messageapp.domain.models.contact_with_group.ContactGroupCrossRef
import com.skillbox.messageapp.domain.models.contact_with_group.ContactWithGroups
import com.skillbox.messageapp.domain.models.contact_with_group.Group
import com.skillbox.messageapp.domain.models.contact_with_group.GroupWithContacts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GroupRepository(application: Application) {

    private val mapper = MapperGroup()
    private val groupDao = MessageDataBase.getInstance(application).groupDao()
    private val contactCroupCrossRefDao = MessageDataBase.getInstance(application)
        .contactGroupCrossRefDao()

    suspend fun addGroup(group: Group) = withContext(Dispatchers.IO) {
        val groupEntity = mapper.mapModelToEntityModel(group)
        groupDao.addGroup(groupEntity)
    }

    suspend fun getGroups(): List<Group> = withContext(Dispatchers.IO) {
        groupDao.getGroups().map {
            mapper.mapEntityModelToModel(it)
        }
    }

    suspend fun getGroupWithContacts(): List<GroupWithContacts> = withContext(Dispatchers.IO) {
        groupDao.getGroupWithContactsEntity().map {
            mapper.mapGroupWithContactsToModel(it)
        }
    }

    suspend fun getContactWithGroups(): List<ContactWithGroups> = withContext(Dispatchers.IO) {
        groupDao.getContactWithGroupsEntity().map {
            mapper.mapContactWithGroupsToModel(it)
        }
    }

    suspend fun getContactGroupCrossRef(): List<ContactGroupCrossRef> =
        withContext(Dispatchers.IO){
            contactCroupCrossRefDao.getContactGroupCrossRef().map {
                mapper.mapContactGroupCrossRefToModel(it)
            }
        }
}