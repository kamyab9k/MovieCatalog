package com.example.movieCatalog.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieCatalog.models.Movie

class MovieViewModel : ViewModel() {

    private val movieRepository = MovieRepository()
    val movieListLiveData: LiveData<List<Movie>> = movieRepository.moviesRepo

    fun fetchMovies() {
        movieRepository.fetchMovies()
    }
}
