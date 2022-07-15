package com.skillbox.messageapp.domain.models

data class User(
    val id: Long,
    val firstName: String,
    val lastName: String?,
    val avatar: String?
)
