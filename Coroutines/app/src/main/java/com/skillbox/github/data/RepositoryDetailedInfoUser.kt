package com.skillbox.github.data

import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class RepositoryDetailedInfoUser {

    suspend fun getDetailedInfo(ownerLogin: String, repoName: String): RemoteRepository =
        Networking.gitHubApi.getDetailedInfo(ownerLogin, repoName)

    suspend fun isStarred(ownerLogin: String, repoName: String): Boolean {
        var call: Call<Unit>? = null

        return suspendCancellableCoroutine<Boolean> { continuation ->
            continuation.invokeOnCancellation {
                call?.cancel()
            }
            call = Networking.gitHubApi.isStarred(ownerLogin, repoName).apply {
            enqueue(
                object : Callback<Unit> {

                    override fun onResponse(
                        call: Call<Unit>,
                        response: Response<Unit>
                    ) {
                        when (response.code()) {
                            204 -> continuation.resume(true)
                            404 -> continuation.resume(false)
                            else -> {
                                val e = RuntimeException("incorrect status code")
                                continuation.resumeWithException(e)
                            }
                        }
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        continuation.resumeWithException(t)
                    }
                }
            )}
        }
    }

    suspend fun setStarred(ownerLogin: String, repoName: String) =
        Networking.gitHubApi.setStarred(ownerLogin, repoName).code() == 204

    suspend fun deleteStarred(ownerLogin: String, repoName: String) =
        Networking.gitHubApi.deleteStarred(ownerLogin, repoName).code() == 204

}