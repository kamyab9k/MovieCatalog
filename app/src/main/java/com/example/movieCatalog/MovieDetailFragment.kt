package com.example.movieCatalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.movieCatalog.databinding.FragmentMovieDetailBinding
import com.example.movieCatalog.models.Movie

class MovieDetailFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = arguments?.getParcelable<Movie>("movieKey")
        //test
        val moviePosterPath = movie?.backdrop_path
        val imageBaseUrl = "https://image.tmdb.org/t/p/w500/"
        val image = imageBaseUrl + moviePosterPath

        Glide.with(requireContext())
            .load(image)
            .into(binding.ImageViewSecondPoster)

        binding.movieTitleDetailFragment.text = movie?.title
        binding.TextMultiLineStory.text = movie?.overview
        binding.MovieReleaseDateDetailFragment.text = movie?.release_date
//        binding.Rating.text = movie?.vote_average
    }
}

