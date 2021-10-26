package com.skillbox.networking

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class CustomAdapterRatings {
//    @ToJson
//    fun fromJson(ratingsFromJson: Map<String, String>): List<Rating> {
//        return ratingsFromJson.map { Rating(it.key, it.value) }
//    }
    @FromJson
    fun toJson(ratingToJson: List<Rating>): Map<String, String>{
        return ratingToJson.associate { Pair(it.source, it.rating) }
    }
}