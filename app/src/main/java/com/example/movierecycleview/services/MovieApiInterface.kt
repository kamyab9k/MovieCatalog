package com.example.movierecycleview.services

import com.example.movierecycleview.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface {
    @GET("/3/movie/popular?api_key=90a54a6b7ac845f17b2b70d45f6c2851")
    fun getMovieList(): Call<MovieResponse>
}