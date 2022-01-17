package com.skillbox.github.ui.repository_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillbox.github.data.RemoteRepository
import com.skillbox.github.data.RepositoryListRepositories
import kotlinx.coroutines.launch

class RepositoryListViewModel : ViewModel() {

    private val repository = RepositoryListRepositories()

    private val _repositories = MutableLiveData<List<RemoteRepository>>()
    private val _errorMessage = MutableLiveData<String>()
    val repositories: LiveData<List<RemoteRepository>>
        get() = _repositories
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun getRepositories() {
        viewModelScope.launch {
            try {
                _repositories.postValue(repository.getRepositories())
            }catch (e: Exception){
                _errorMessage.postValue(e.message)
            }

        }
    }
}