package com.skillbox.github.ui.current_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.github.data.RemoteUser
import com.skillbox.github.data.UserRepository
import retrofit2.Call

class CurrentUserViewModel : ViewModel() {
    private val repository = UserRepository()
    private val _remoteUser = MutableLiveData<RemoteUser>()
    private val _errorMessage = MutableLiveData<String>()
    private var currentCall: Call<RemoteUser>? = null

    val remoteUser: LiveData<RemoteUser>
        get() = _remoteUser
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun getUser() {
        currentCall = repository.getUser({
            _remoteUser.postValue(it)
        },
        { error ->
            _errorMessage.postValue(error.message)
        })

        currentCall = null
    }

    override fun onCleared() {
        super.onCleared()
        currentCall = null
    }
}