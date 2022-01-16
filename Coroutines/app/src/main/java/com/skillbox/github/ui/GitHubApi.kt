package com.skillbox.github.ui

import com.skillbox.github.data.RemoteRepository
import com.skillbox.github.data.RemoteUser
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface GitHubApi {
    @GET("/user")
    suspend fun getUser(): RemoteUser

    @GET("/repositories")
    suspend fun getRepositories(): List<RemoteRepository>

    @GET("/repos/{owner}/{repo}")
    suspend fun getDetailedInfo(
        @Path("owner") ownerLogin: String,
        @Path("repo") repoName: String
    ): RemoteRepository

    @GET("/user/starred/{owner}/{repo}")
    suspend fun isStarred(
        @Path("owner") ownerLogin: String,
        @Path("repo") repoName: String
    )

    @PUT("/user/starred/{owner}/{repo}")
    suspend fun setStarred(
        @Path("owner") ownerLogin: String,
        @Path("repo") repoName: String
    )

    @DELETE("/user/starred/{owner}/{repo}")
    suspend fun deleteStarred(
        @Path("owner") ownerLogin: String,
        @Path("repo") repoName: String
    )
}