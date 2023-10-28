package com.example.movieCatalog

import android.health.connect.datatypes.units.Length
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieCatalog.services.MovieViewModel
import com.example.movieCatalog.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import menuFragments.AboutFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MovieViewModel
    private val movieAdapter = MovieAdaptor(this)
    private val homeFragment = HomeFragment()
    private val aboutFragment = AboutFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val bottomNav = binding.bottomNavigationView

        //test
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> replaceFragment(homeFragment)
                R.id.about -> replaceFragment(aboutFragment)
                // Handle more items if needed
            }
            true
        }



        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

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

    //test
    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
