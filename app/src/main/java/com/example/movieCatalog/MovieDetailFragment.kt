package com.example.movieCatalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieCatalog.models.Movie

class MovieDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)

        // Retrieve movie data from arguments
        val movie = arguments?.getParcelable<Movie>("movie")

        // Display the movie details in your fragment's layout

        return view
    }
}
