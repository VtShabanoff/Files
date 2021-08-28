package com.skillbox.permissionsanddate

import org.threeten.bp.Instant

data class MyLocation(
    val id: Long,
    val location: String,
    var createdAt: Instant,
    val avatarLink: String

    )
