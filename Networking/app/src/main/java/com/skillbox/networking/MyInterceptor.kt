package com.skillbox.networking

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newUrl = request.url.newBuilder()
            .addQueryParameter("apikey", MOVIE_API_KEY)
            .build()

        val newRequest = request.newBuilder()
            .get()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }

    companion object{
        private const val MOVIE_API_KEY = "d9ffc114"
    }
}