package com.skillbox.messageapp.data.db

import com.skillbox.messageapp.data.db.entities.groups_to_contact.ContactGroupCrossRefEntity
import com.skillbox.messageapp.data.db.entities.groups_to_contact.ContactWithGroupsEntity
import com.skillbox.messageapp.data.db.entities.groups_to_contact.GroupEntity
import com.skillbox.messageapp.data.db.entities.groups_to_contact.GroupWithContactsEntity
import com.skillbox.messageapp.domain.models.Contact
import com.skillbox.messageapp.domain.models.contact_with_group.ContactGroupCrossRef
import com.skillbox.messageapp.domain.models.contact_with_group.ContactWithGroups
import com.skillbox.messageapp.domain.models.contact_with_group.Group
import com.skillbox.messageapp.domain.models.contact_with_group.GroupWithContacts

class MapperGroup {

    fun mapEntityModelToModel(entityGroup: GroupEntity) = Group(
        groupId = entityGroup.groupId,
        name = entityGroup.name
    )

    fun mapModelToEntityModel(group: Group) = GroupEntity(
        groupId = group.groupId,
        name = group.name
    )

    fun mapGroupWithContactsToModel(
        groupWithContactsEntity: GroupWithContactsEntity
    ) = GroupWithContacts(
        group = Group(
            groupId = groupWithContactsEntity.groupEntity.groupId,
            name = groupWithContactsEntity.groupEntity.name
        ),
        contacts = groupWithContactsEntity.contactsEntity.map { entityContact ->
            Contact(
                id = entityContact.id,
                name = entityContact.name
            )
        }
    )

    fun mapContactWithGroupsToModel(
        contactWithGroupsEntity: ContactWithGroupsEntity
    ) = ContactWithGroups(
        contact = Contact(
            id = contactWithGroupsEntity.contactEntity.id,
            name = contactWithGroupsEntity.contactEntity.name
        ),
        groups = contactWithGroupsEntity.groupsEntity.map { groupEntity ->
            Group(
                groupId = groupEntity.groupId,
                name = groupEntity.name
            )
        }
    )

    fun mapContactGroupCrossRefToModel(
        contactGroupCrossRefEntity: ContactGroupCrossRefEntity
    ) = ContactGroupCrossRef(
        contactId = contactGroupCrossRefEntity.contactId,
        groupId = contactGroupCrossRefEntity.groupId
    )
}