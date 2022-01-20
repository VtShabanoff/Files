package com.skillbox.github.data

class RepositoryListRepositories {

    suspend fun getRepositories() =
        Networking.gitHubApi.getRepositories()
}