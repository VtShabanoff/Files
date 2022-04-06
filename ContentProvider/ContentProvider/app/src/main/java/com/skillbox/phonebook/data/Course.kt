package com.skillbox.phonebook.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Course(
    val id: Long,
    val title: String
)
