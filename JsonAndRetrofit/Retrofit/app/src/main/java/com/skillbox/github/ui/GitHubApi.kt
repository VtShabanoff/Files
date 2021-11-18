package com.skillbox.github.ui

import com.skillbox.github.data.RemoteUser
import retrofit2.Call
import retrofit2.http.GET

interface GitHubApi {
    @GET("/user")
    fun getUser(): Call<RemoteUser>
}