package com.skillbox.messageapp.domain.models

data class UsersAndContacts(
    val user: User,
    val contacts: List<Contact>
)