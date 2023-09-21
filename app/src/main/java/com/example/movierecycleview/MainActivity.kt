package com.example.movierecycleview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movierecycleview.databinding.ActivityMainBinding
import com.example.movierecycleview.models.Movie
import com.example.movierecycleview.models.MovieResponse
import com.example.movierecycleview.services.MovieApiInterface
import com.example.movierecycleview.services.MovieApiService
import com.example.movierecycleview.services.MovieViewModel
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        val movieAdapter = MovieAdaptor(this)

        binding.rvMoviesList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        // Observe the movies LiveData from the ViewModel
        viewModel.movies.observe(this) { movies ->
            movieAdapter.updateMovies(movies)
        }
        viewModel.fetchMovies()
    }
}
