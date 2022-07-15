package com.skillbox.messageapp.domain.models.contact_with_group

import com.skillbox.messageapp.domain.models.Contact

data class GroupWithContacts(
    val group: Group,
    val contacts: List<Contact>
)
