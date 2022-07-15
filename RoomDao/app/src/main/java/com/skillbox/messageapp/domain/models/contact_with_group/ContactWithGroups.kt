package com.skillbox.messageapp.domain.models.contact_with_group

import com.skillbox.messageapp.domain.models.Contact

data class ContactWithGroups(
    val contact: Contact,
    val groups: List<Group>
)
