package com.skillbox.networking.networking

import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

object NetWork {
    private const val MOVIE_API_KEY = "d9ffc114"

    private val client = OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    fun getSearchMovieCall(text: String, year: String, type: String): Call {
        //http://www.omdbapi.com/?apikey=[yourkey]&s=

        val url = HttpUrl.Builder()
            .scheme("http")
            .host("www.omdbapi.com")
            .addQueryParameter("apikey", MOVIE_API_KEY)
            .addQueryParameter("s", text)
            .addQueryParameter("type", type)
            .addQueryParameter("y", year)
            .build()

        val request = Request.Builder()
            .get()
            .url(url)
            .build()
        return client.newCall(request)
    }
}