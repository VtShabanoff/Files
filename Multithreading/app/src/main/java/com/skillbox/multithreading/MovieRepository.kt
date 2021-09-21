package com.skillbox.multithreading

import com.skillbox.multithreading.networking.Movie
import com.skillbox.multithreading.networking.Network

class MovieRepository {
     fun getMovieById(movieIds: String): Movie? {
         return Network.api().getMovieById(movieIds, Network.MOVIE_API_KEY).execute().body()
     }

}