package com.skillbox.github.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteUser(
    @Json(name = "login")
    var name: String,
    @Json(name = "avatar_url")
    var avatar: String?,
    val id: Long
)
