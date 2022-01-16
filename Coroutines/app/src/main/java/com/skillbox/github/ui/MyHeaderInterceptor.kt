package com.skillbox.github.ui

import com.skillbox.github.data.Token
import okhttp3.Interceptor
import okhttp3.Response

class MyHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val header = chain.request().newBuilder()
            .addHeader("Authorization", "token ${Token.accessToken}")
            .build()
        return chain.proceed(header)
    }
}