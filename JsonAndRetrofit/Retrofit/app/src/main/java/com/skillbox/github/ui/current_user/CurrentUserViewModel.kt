package com.skillbox.github.ui.current_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.github.data.RemoteUser
import com.skillbox.github.data.UserRepository

class CurrentUserViewModel : ViewModel() {
    private val repository = UserRepository()
    private val _remoteUser = MutableLiveData<RemoteUser>()
    val remoteUser: LiveData<RemoteUser>
        get() = _remoteUser

    fun getUser(){
        repository.getUser({_remoteUser.postValue(it)}, {})
    }
}