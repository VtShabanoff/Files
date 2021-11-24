package com.skillbox.github.ui

import com.skillbox.github.data.RemoteRepository
import com.skillbox.github.data.RemoteUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("/user")
    fun getUser(): Call<RemoteUser>

    @GET("/repositories")
    fun getRepositories(): Call<List<RemoteRepository>>

    @GET("/repos/{owner}/{repo}")
    fun getDetailedInfo(
        @Path("owner") ownerLogin: String,
        @Path("repo") repoName: String
    ): Call<RemoteRepository>

    @GET("/user/starred/{owner}/{repo}")
    fun isStarred(
        @Path("owner") ownerLogin: String,
        @Path("repo") repoName: String
    ): Call<Unit>
}