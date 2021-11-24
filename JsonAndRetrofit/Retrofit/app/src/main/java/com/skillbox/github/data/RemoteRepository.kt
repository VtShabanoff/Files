package com.skillbox.github.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteRepository(
    val id: Long,
    @Json(name = "node_id")
    val nodeId: String,
    val name: String,
    val owner: Owner
)

@JsonClass(generateAdapter = true)
data class Owner(
    @Json(name = "login")
    val nameOwner: String,
    val id: Long,
    @Json(name = "avatar_url")
    val avatar: String
)
