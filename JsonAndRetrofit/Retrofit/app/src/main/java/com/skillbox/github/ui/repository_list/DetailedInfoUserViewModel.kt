package com.skillbox.github.ui.repository_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.github.data.RemoteRepository
import com.skillbox.github.data.RepositoryDetailedInfoUser
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

    private var currentCall: Call<RemoteRepository>? = null
    private var currentCallIsStarred: Call<Unit>? = null

    fun getDetailedInfo(ownerLogin: String, repoName: String) {
        currentCall = repository.getDetailedInfo(ownerLogin, repoName,
            { remoteRepository ->
                _detailedInfo.postValue(remoteRepository)
            }) {
            _errorMessage.postValue(it.message)
        }
        currentCall = null

    }

    fun isStarred(ownerLogin: String, repoName: String){
        currentCallIsStarred = repository.isStarred(ownerLogin, repoName, { isStarred ->
            _isStarred.postValue(isStarred)
        }){
            _errorMessageIsStarred.postValue(it.message)
        }
        currentCallIsStarred = null
    }

    override fun onCleared() {
        super.onCleared()
        currentCall = null
        currentCallIsStarred = null
    }
}