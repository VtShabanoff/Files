package com.skillbox.github.ui.current_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillbox.github.data.RemoteUser
import com.skillbox.github.data.UserRepository
import kotlinx.coroutines.handleCoroutineException
import kotlinx.coroutines.launch
import retrofit2.Call

class CurrentUserViewModel : ViewModel() {
    private val repository = UserRepository()
    private val _remoteUser = MutableLiveData<RemoteUser>()
    private val _errorMessage = MutableLiveData<String>()

    val remoteUser: LiveData<RemoteUser>
        get() = _remoteUser
    val errorMessage: LiveData<String>
        get() = _errorMessage

    suspend fun getUser() {
        viewModelScope.launch {
            try {
                _remoteUser.postValue(repository.getUser())
            } catch (e: Exception){
                _errorMessage.postValue(e.message)
            }
        }
    }
}