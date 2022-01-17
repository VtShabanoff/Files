package com.skillbox.github.ui.repository_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillbox.github.data.RemoteRepository
import com.skillbox.github.data.RepositoryDetailedInfoUser
import kotlinx.coroutines.launch
import retrofit2.Call

class DetailedInfoUserViewModel : ViewModel() {

    private val repository = RepositoryDetailedInfoUser()

    private val _detailedInfo = MutableLiveData<RemoteRepository>()
    val detailedInfo: LiveData<RemoteRepository>
        get() = _detailedInfo

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _isStarred = MutableLiveData<Boolean>()
    val isStarred: LiveData<Boolean>
        get() = _isStarred

    private val _errorMessageIsStarred = MutableLiveData<String>()
    val errorMessageIsStarred: LiveData<String>
        get() = _errorMessageIsStarred
    private val _errorMessageSetStarred = MutableLiveData<String>()
    val errorMessageSetStarred: LiveData<String>
        get() = _errorMessageSetStarred
    private val _errorMessageDeleteStarred = MutableLiveData<String>()
    val errorMessageDeleteStarred: LiveData<String>
        get() = _errorMessageDeleteStarred

    fun getDetailedInfo(ownerLogin: String, repoName: String) {
        viewModelScope.launch {
            try {
                _detailedInfo.postValue(repository.getDetailedInfo(ownerLogin, repoName))
            } catch (e: Exception) {
                _errorMessage.postValue(e.message)
            }
        }
    }

    fun isStarred(ownerLogin: String, repoName: String) {
        viewModelScope.launch {
            try {
                _isStarred.postValue(repository.isStarred(ownerLogin, repoName))
            } catch (e: Exception) {
                _errorMessageIsStarred.postValue(e.message)
            }
        }
    }

    fun deleteStarred(ownerLogin: String, repoName: String) {
        viewModelScope.launch {
            try {
                _isStarred.postValue(!repository.deleteStarred(ownerLogin, repoName))
            }catch (e: Exception){
                _errorMessageDeleteStarred.postValue(e.message)
            }
        }
    }

    fun setStarred(ownerLogin: String, repoName: String){
        viewModelScope.launch {
            try {
                _isStarred.postValue(repository.setStarred(ownerLogin, repoName))
            }catch (e: Exception){
                _errorMessageSetStarred.postValue(e.message)
            }
        }
    }
}