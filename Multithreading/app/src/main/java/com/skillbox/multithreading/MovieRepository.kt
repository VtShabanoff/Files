package com.skillbox.multithreading

import com.skillbox.multithreading.networking.Movie
import com.skillbox.multithreading.networking.Network

class MovieRepository {
     private fun getMovieById(movieIds: String): Movie? {
         return Network.api().getMovieById(movieIds, Network.MOVIE_API_KEY).execute().body()
     }

    fun fetchMovies(
        movieIds: List<String>,
        onMoviesFetched: (movies: List<Movie>) -> Unit
    ){
        Thread {

            val fMovies = movieIds.mapNotNull { movieId ->
                getMovieById(movieId)
            }

            onMoviesFetched(fMovies)
        }.start()
    }

}