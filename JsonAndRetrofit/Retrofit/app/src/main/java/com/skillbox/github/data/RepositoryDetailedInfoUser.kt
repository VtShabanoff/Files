package com.skillbox.github.data

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryDetailedInfoUser {
    fun getDetailedInfo(
        ownerLogin: String,
        repoName: String,
        onComplete: (RemoteRepository) -> Unit,
        onError: (Throwable) -> Unit
    ): Call<RemoteRepository> =
        Networking.gitHubApi.getDetailedInfo(ownerLogin, repoName).apply {
            enqueue(
                object : Callback<RemoteRepository> {

                    override fun onResponse(
                        call: Call<RemoteRepository>,
                        response: Response<RemoteRepository>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let { onComplete(it) }
                            Log.d("onDetailedInfo", "response.body() = ${response.body()}")
                        } else {
                            onError(RuntimeException("incorrect status code"))
                        }
                    }

                    override fun onFailure(call: Call<RemoteRepository>, t: Throwable) {
                        onError(t)
                    }
                }
            )
        }

    fun isStarred(
        ownerLogin: String,
        repoName: String,
        onComplete: (Boolean) -> Unit,
        onError: (Throwable) -> Unit
    ): Call<Unit> =
        Networking.gitHubApi.isStarred(ownerLogin, repoName).apply {
            enqueue(
                object : Callback<Unit> {

                    override fun onResponse(
                        call: Call<Unit>,
                        response: Response<Unit>
                    ) {
                        when (response.code()) {
                            204 -> onComplete(true)
                            404 -> onComplete(false)
                            else -> onError(RuntimeException("incorrect status code"))
                        }
                        Log.d("isStarred", "response.code() = ${response.code()}")
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        onError(t)
                    }
                }
            )
        }
}