package com.skillbox.github.data

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryListRepositories {

    suspend fun getRepositories() =
        Networking.gitHubApi.getRepositories()

//    fun getRepositories(
//        onComplete: (List<RemoteRepository>) -> Unit,
//        onError: (Throwable) -> Unit
//    ) {
//        Networking.gitHubApi.getRepositories().enqueue(
//            object : Callback<List<RemoteRepository>> {
//
//                override fun onResponse(call: Call<List<RemoteRepository>>, response: Response<List<RemoteRepository>>) {
//                    if (response.isSuccessful) {
//                        onComplete(response.body().orEmpty())
//                        Log.d("onResponseRepo", "response.body() = ${response.body()}")
//                    } else {
//                        onError(RuntimeException("incorrect status code"))
//                    }
//                }
//
//                override fun onFailure(call: Call<List<RemoteRepository>>, t: Throwable) {
//                    onError(t)
//                }
//            }
//        )
//    }
}