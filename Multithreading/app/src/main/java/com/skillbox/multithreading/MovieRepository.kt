package com.skillbox.multithreading

import com.skillbox.multithreading.networking.Movie
import com.skillbox.multithreading.networking.Network
import java.util.*

class MovieRepository {
     private fun getMovieById(movieIds: String): Movie? {
         return Network.api().getMovieById(movieIds, Network.MOVIE_API_KEY).execute().body()
     }

    fun fetchMovies(
        movieIds: List<String>,
        onMoviesFetched: (movies: List<Movie>) -> Unit
    ){
        Thread {
            val allMovies = Collections.synchronizedList(mutableListOf<Movie>())
            val threads = movieIds.chunked(1).map { movieChunk ->
                Thread{
                    val fMovies = movieChunk.mapNotNull { movieId ->
                        getMovieById(movieId)
                    }
                    allMovies.addAll(fMovies)
                }
            }
              threads.forEach { it.start() }
              threads.forEach { it.join() }
            onMoviesFetched(allMovies)
        }.start()
    }

}