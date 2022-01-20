package com.skillbox.github.ui

import com.skillbox.github.data.FollowingUser
import com.skillbox.github.data.RemoteRepository
import com.skillbox.github.data.RemoteUser
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface GitHubApi {
    @GET("/user")
    suspend fun getUser(): RemoteUser

    @GET("/user/following")
    suspend fun getUserListFollowing():List<FollowingUser>

    @GET("/repositories")
    suspend fun getRepositories(): List<RemoteRepository>

    @GET("/repos/{owner}/{repo}")
    suspend fun getDetailedInfo(
        @Path("owner") ownerLogin: String,
        @Path("repo") repoName: String
    ): RemoteRepository

    @GET("/user/starred/{owner}/{repo}")
    fun isStarred(
        @Path("owner") ownerLogin: String,
        @Path("repo") repoName: String
    ):Call<Unit>

    @PUT("/user/starred/{owner}/{repo}")
    suspend fun setStarred(
        @Path("owner") ownerLogin: String,
        @Path("repo") repoName: String
    ): Response<Unit>

    @DELETE("/user/starred/{owner}/{repo}")
    suspend fun deleteStarred(
        @Path("owner") ownerLogin: String,
        @Path("repo") repoName: String
    ): Response<Unit>
}