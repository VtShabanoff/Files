package com.skillbox.github.data

class UserRepository {

    suspend fun getUser(): RemoteUser =
        Networking.gitHubApi.getUser()

    suspend fun getUserListFollowing(): List<FollowingUser> =
        Networking.gitHubApi.getUserListFollowing()
}