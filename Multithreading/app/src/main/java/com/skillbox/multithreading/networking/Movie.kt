package com.skillbox.multithreading.networking

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Long,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: Int
)