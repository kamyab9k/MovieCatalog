package com.example.movieCatalog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieCatalog.services.MovieViewModel
import com.example.movieCatalog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel : MovieViewModel
    private val movieAdapter = MovieAdaptor(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        setupMovieRecyclerView()
        getMovieData()
        observeMovieData()
    }

    private fun observeMovieData() {
        viewModel.movieListLiveData.observe(this) { movies ->
            movieAdapter.updateMovies(movies)
        }
    }

    private fun getMovieData() {
        viewModel.fetchMovies()
    }

    private fun setupMovieRecyclerView() {
        binding.rvMoviesList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

}
