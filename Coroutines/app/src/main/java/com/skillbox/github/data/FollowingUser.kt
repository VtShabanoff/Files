package com.skillbox.github.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FollowingUser(
    @Json(name = "login")
    var name: String,
    val id: Long
)
