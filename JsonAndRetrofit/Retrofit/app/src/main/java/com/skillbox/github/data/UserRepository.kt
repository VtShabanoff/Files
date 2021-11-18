package com.skillbox.github.data

import android.util.Log
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    private val moshi: Moshi = Moshi.Builder().build()

    fun getUser(
        onComplete: (RemoteUser) -> Unit,
        onError: (Throwable) -> Unit
    ){
        Networking.gitHubApi.getUser().enqueue(
            object : Callback<RemoteUser>{

                override fun onResponse(call: Call<RemoteUser>, response: Response<RemoteUser>) {
                    if (response.isSuccessful){
                        response.body()?.let { onComplete(it) }
                        Log.d("onResponse", "response.body() = ${response.body()}")
                    } else {
                        onError(RuntimeException("incorrect status code"))
                    }
                }

                override fun onFailure(call: Call<RemoteUser>, t: Throwable) {
                    onError(t)
                }
            }
        )
    }
}