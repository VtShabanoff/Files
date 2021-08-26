package com.skillbox.permissionsanddate

import org.threeten.bp.Instant

data class MyLocation(
    val id: Long,
    val location: String,
    val createdAt: Instant,
    val avatarLink: String

    )
