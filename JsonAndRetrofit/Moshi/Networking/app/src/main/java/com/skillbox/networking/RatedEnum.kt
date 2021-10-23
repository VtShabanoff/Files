package com.skillbox.networking

import com.squareup.moshi.Json

enum class RatedEnum {
    G,
    PG,
    @Json(name = "PG-13")
    PG_13,
    R,
    @Json(name = "NC-17")
    NC_17,
    @Json(name = "N/A")
    NA
}