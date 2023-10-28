package com.example.movieCatalog.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieCatalog.models.Movie
import com.example.movieCatalog.models.MovieResponse
import retrofit2.Call
import retrofit2.Response

class MovieRepository {
    private val _movies = MutableLiveData<List<Movie>>()
    val moviesRepo: LiveData<List<Movie>> get() = _movies

    fun fetchMovies() {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : retrofit2.Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // for Handle failure or error here if needed
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                _movies.postValue(response.body()?.movies)
            }
        })
    }

    fun fetchMovies2() {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
    }
}

