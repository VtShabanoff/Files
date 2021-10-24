package com.skillbox.networking

import com.skillbox.networking.networking.NetWork
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class RepositoryMovieList {

    fun searchMovie(
        title: String,
        year: String,
        type: String,
        callback: (List<Movie>) -> Unit,
        callbackError: (String, Boolean) -> Unit
    ): Call {
        return NetWork.getSearchMovieCall(title, year, type).apply {
            enqueue(object: Callback{
                override fun onFailure(call: Call, e: IOException) {
                    e.message?.let { callbackError(it, true) }
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful){
                        val responseString = response.body?.string().orEmpty()
                        val movies = parseMovieResponse(responseString)
                        callback(movies)
                    }else{
                        callback(emptyList())
                    }
                }

            })
        }
    }

    fun parseMovieResponse(responseString: String): List<Movie> {
        val moshi = Moshi.Builder().build()

        val arrayJsonMovies = Types.newParameterizedType(
                    List::class.java,
                    Movie::class.java
                )

        val adapter = moshi.adapter<List<Movie>>(arrayJsonMovies).nonNull()
        val search = adapter.fromJson(responseString) ?: error("error parsing from response")
        return search
    }

//    private fun parseMovieResponse(responseString: String): List<Movie>{
//        try {
//            val jsonObject = JSONObject(responseString)
//            val movieArray = jsonObject.getJSONArray("Search")
//
//            // тот же самый forEach, но только его нет в JSON библиотеке
//            val movies = (0 until movieArray.length()).map { index -> movieArray.getJSONObject(index) }
//                .map { movieJsonObject ->
//
//                    val titleJSON = movieJsonObject.getString("Title")
//                    val yearJSON = movieJsonObject.getString("Year")
//                    val idJSON = movieJsonObject.getString("imdbID")
//                    val typeJSON = movieJsonObject.getString("Type")
//                    val posterLinkJSON = movieJsonObject.getString("Poster")
//
//                    Movie(
//                        id = idJSON,
//                        title = titleJSON,
//                        year = yearJSON,
//                        type = typeJSON,
//                        poster = posterLinkJSON
//                    )
//                }
//            return movies
//        } catch (e: JSONException){
//            Log.d("TAG", "server pars json error = ${e.message}", e)
//            return emptyList()
//        }
//    }
}