package com.example.movieCatalog.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieCatalog.models.Movie
import com.example.movieCatalog.models.MovieResponse
import retrofit2.Call
import retrofit2.Response

class MovieViewModel : ViewModel() {

    private val movieRepository = MovieRepository()
    private val _movies = MutableLiveData<List<Movie>>()

    val moviesViewModel: LiveData<List<Movie>> = movieRepository.moviesRepo

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        movieRepository.fetchMovies()
    }
}
