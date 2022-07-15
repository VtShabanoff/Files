package com.skillbox.messageapp.data.db

import com.skillbox.messageapp.data.db.entities.EntityContact
import com.skillbox.messageapp.data.db.entities.EntityUsersAndEntityContacts
import com.skillbox.messageapp.domain.models.Contact
import com.skillbox.messageapp.domain.models.User
import com.skillbox.messageapp.domain.models.UsersAndContacts

class MapperContact {

    fun mapModelToEntityModel(contact: Contact) = EntityContact(
        id = contact.id,
        name = contact.name
    )

    fun mapEntityModelToModel(entityContact: EntityContact) = Contact(
        id = entityContact.id,
        name = entityContact.name
    )

    fun mapEntityUsersAndEntityContacts(
        entityUsersAndEntityContacts: EntityUsersAndEntityContacts
    ) = UsersAndContacts(
        user = User(
            id = entityUsersAndEntityContacts.entityUser.id,
            firstName = entityUsersAndEntityContacts.entityUser.firstName,
            lastName = entityUsersAndEntityContacts.entityUser.lastName,
            avatar = entityUsersAndEntityContacts.entityUser.avatar
        ),
        contacts = entityUsersAndEntityContacts.entityContacts.map {
            Contact(
                id = it.id,
                name = it.name
            )
        }
    )
}