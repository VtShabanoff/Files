package com.skillbox.messageapp.ui.user_ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.skillbox.messageapp.data.repositories.UserRepository
import com.skillbox.messageapp.domain.models.Account
import com.skillbox.messageapp.domain.models.User
import com.skillbox.messageapp.domain.models.UserAndAccount
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = UserRepository(application)
    private val _usersAndAccounts = MutableLiveData<List<UserAndAccount>>()
    val usersAndAccounts: LiveData<List<UserAndAccount>> = _usersAndAccounts


    fun addUser(user: User) {
        viewModelScope.launch {
            repo.addUser(user)
        }
    }

    fun addAccount(account: Account) {
        viewModelScope.launch {
            repo.addAccount(account)
        }
    }

    fun deleteUser(id: Long) {
        viewModelScope.launch {
            repo.deleteUser(id)
        }
    }

    fun getUsersAndAccounts() {
        viewModelScope.launch {
            _usersAndAccounts.postValue(repo.getUsersAndAccounts().map { entity ->
                UserAndAccount(
                    user = User(
                        id = entity.entityUser.id,
                        firstName = entity.entityUser.firstName,
                        lastName = entity.entityUser.lastName,
                        avatar = entity.entityUser.avatar
                    ),
                    account = Account(
                        entity.entityAccount.id,
                        entity.entityAccount.email
                    )
                )
            })
        }

    }
}