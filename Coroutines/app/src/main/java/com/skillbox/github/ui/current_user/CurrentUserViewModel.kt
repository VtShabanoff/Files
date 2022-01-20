package com.skillbox.github.ui.current_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillbox.github.data.FollowingUser
import com.skillbox.github.data.RemoteUser
import com.skillbox.github.data.UserRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.handleCoroutineException
import kotlinx.coroutines.launch
import retrofit2.Call

class CurrentUserViewModel : ViewModel() {
    private val repository = UserRepository()
    private val _remoteUser = MutableLiveData<RemoteUser>()
    private val _followingUser = MutableLiveData<List<FollowingUser>>()
    private val _errorMessage = MutableLiveData<String>()

    val remoteUser: LiveData<RemoteUser>
        get() = _remoteUser
    val followingUser: LiveData<List<FollowingUser>>
        get() = _followingUser
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun getUserAndUserListFollowing() {
        viewModelScope.launch {
            val user = async(CoroutineExceptionHandler { _, t ->
                _errorMessage.postValue(t.message)
            }) {
                repository.getUser()
            }
            _remoteUser.postValue(user.await())

            val followUser = async(CoroutineExceptionHandler { _, t ->
                _errorMessage.postValue(t.message)
            }) {
                repository.getUserListFollowing()
            }
            _followingUser.postValue(followUser.await())
        }
    }
}