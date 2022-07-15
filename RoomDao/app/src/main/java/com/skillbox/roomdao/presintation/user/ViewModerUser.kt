package com.skillbox.roomdao.presintation.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.skillbox.roomdao.data.entities.EEmail
import com.skillbox.roomdao.data.entities.EUser
import com.skillbox.roomdao.data.entities.EUserWithComponent
import com.skillbox.roomdao.data.user.repositories.UserRepository
import kotlinx.coroutines.launch

class ViewModerUser(context: Application) : AndroidViewModel(context) {
    private val repository = UserRepository(context)

    private val _usersWithEmail = MutableLiveData<List<EUserWithComponent>>()
    val usersWithEmail: LiveData<List<EUserWithComponent>> = _usersWithEmail

    fun getUser(id: Long){
        viewModelScope.launch {
            repository.getUser(id)
        }
    }

    fun addUser(eUser: EUser){
        viewModelScope.launch {
            repository.addUser(eUser)
        }
    }

    fun addEmail(eEmail: EEmail){
        viewModelScope.launch {
            repository.addEmail(eEmail)
        }
    }

    fun getUserWithEmail(){
        viewModelScope.launch {
            _usersWithEmail.postValue(repository.getListUserWithEmail())
        }
    }
}