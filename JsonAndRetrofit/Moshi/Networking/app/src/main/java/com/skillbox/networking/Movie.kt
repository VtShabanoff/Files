package com.skillbox.networking

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "imdbID")
    val id: String,
    @Json(name = "Title")
    val title: String,
    @Json(name = "Year")
    val year: String,
    @Json(name = "Rated")
    val rated: RatedEnum = RatedEnum.NA,
    @Json(name = "Genre")
    val genre: String?,
    @Json(name = "Type")
    val type: String,
    @Json(name = "Poster")
    val poster: String,
    @Json(name = "Ratings")
    val ratings: Map<String, String>
)