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
    private val _setStarred = MutableLiveData<Boolean>()
    val setStarred: LiveData<Boolean>
        get() = _setStarred
    private val _deleteStarred = MutableLiveData<Boolean>()
    val deleteStarred: LiveData<Boolean>
        get() = _deleteStarred
    private val _errorMessageSetStarred = MutableLiveData<String>()
    val errorMessageSetStarred: LiveData<String>
        get() = _errorMessageSetStarred
    private val _errorMessageDeleteStarred = MutableLiveData<String>()
    val errorMessageDeleteStarred: LiveData<String>
        get() = _errorMessageDeleteStarred

    private var currentCallForDetailedInfo: Call<RemoteRepository>? = null
    private var currentCallIsStarred: Call<Unit>? = null
    private var currentCallSetStarred: Call<Unit>? = null
    private var currentCallDeleteStarred: Call<Unit>? = null

    fun getDetailedInfo(ownerLogin: String, repoName: String) {
        currentCallForDetailedInfo = repository.getDetailedInfo(ownerLogin, repoName,
            { remoteRepository ->
                _detailedInfo.postValue(remoteRepository)
            }) {
            _errorMessage.postValue(it.message)
        }
        currentCallForDetailedInfo = null

    }

    fun isStarred(ownerLogin: String, repoName: String) {
        currentCallIsStarred = repository.isStarred(ownerLogin, repoName, { isStarred ->
            _isStarred.postValue(isStarred)
        }) {
            _errorMessageIsStarred.postValue(it.message)
        }
        currentCallIsStarred = null
    }

    fun setStarred(ownerLogin: String, repoName: String) {
        currentCallSetStarred = repository.setStarred(ownerLogin, repoName, { isSetStarred ->
            _setStarred.postValue(isSetStarred)
        }, {
            _errorMessageSetStarred.postValue(it.message)
        })
        currentCallSetStarred = null
    }

    fun deleteStarred(ownerLogin: String, repoName: String) {
        currentCallDeleteStarred =
            repository.deleteStarred(ownerLogin, repoName, { isDeleteStarred ->
                _deleteStarred.postValue(isDeleteStarred)
            }, {
                _errorMessageDeleteStarred.postValue(it.message)
            })
    }

    override fun onCleared() {
        super.onCleared()
        currentCallForDetailedInfo = null
        currentCallIsStarred = null
        currentCallSetStarred = null
        currentCallDeleteStarred =null
    }
}