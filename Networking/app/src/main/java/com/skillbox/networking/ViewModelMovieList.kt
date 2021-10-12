package com.skillbox.networking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.Call

class ViewModelMovieList: ViewModel() {
    private val repository = RepositoryMovieList()

    var currentCall: Call? = null

    private val moviesLiveData = MutableLiveData<List<Movie>>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()
    private val messageErrorLiveData = MutableLiveData<String>()
    private val isMessageErrorLiveData = MutableLiveData<Boolean>()

    val movies: LiveData<List<Movie>>
        get() = moviesLiveData
    val isLoadingListMovie: LiveData<Boolean>
        get() = isLoadingLiveData
    val massageError: LiveData<String>
        get() = messageErrorLiveData
    val isMessage: LiveData<Boolean>
        get() = isMessageErrorLiveData

    fun search(text: String, year: String, type: String){
        isLoadingLiveData.postValue(true)
        currentCall = repository.searchMovie(text, year, type, {movies ->
            isLoadingLiveData.postValue(false)
            moviesLiveData.postValue(movies)

        }){errorMessage, isMessage->
            messageErrorLiveData.postValue(errorMessage)
            isMessageErrorLiveData.postValue(isMessage)
        }
        currentCall = null
    }

    override fun onCleared() {
        super.onCleared()
        currentCall?.cancel()
    }
}