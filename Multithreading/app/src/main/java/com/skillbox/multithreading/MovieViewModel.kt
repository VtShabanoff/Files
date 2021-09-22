package com.skillbox.multithreading

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.multithreading.networking.Movie

class MovieViewModel: ViewModel() {
    private val movieRepository = MovieRepository()

    private val moviesLiveData = MutableLiveData<List<Movie>>()

    val movies: LiveData<List<Movie>>
        get() = moviesLiveData

    private val movieIds = listOf(
        "tt0111161",
        "tt0068646",
        "tt0071562",
        "tt0468569",
        "tt0050083",
        "tt0108052",
        "tt0167260",
        "tt0110912",
        "tt0060196",
        "tt0120737"
    )

    fun requestMovie(){
        movieRepository.fetchMovies(movieIds){
            movies ->
            moviesLiveData.postValue(movies)
        }
    }

}