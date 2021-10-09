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

    val movies: LiveData<List<Movie>>
        get() = moviesLiveData
    val isLoadingListMovie: LiveData<Boolean>
        get() = isLoadingLiveData

    fun search(text: String, year: String, type: String){
        isLoadingLiveData.postValue(true)
        currentCall = repository.searchMovie(text, year, type){movies ->
            isLoadingLiveData.postValue(false)
            moviesLiveData.postValue(movies)
            currentCall = null
        }
    }

    override fun onCleared() {
        super.onCleared()
        currentCall?.cancel()
    }
}