package com.example.movierecycleview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movierecycleview.databinding.MovieItemBinding
import com.example.movierecycleview.models.Movie


class MovieAdaptor(
    private val context: Context,
) : RecyclerView.Adapter<MovieAdaptor.MovieViewHolder>() {

    private var movies: List<Movie> = emptyList()

    inner class MovieViewHolder(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.apply {
            val moviePosterPath = movies[position].poster_path
            val imageBaseUrl = "https://image.tmdb.org/t/p/w500/"
            val image = imageBaseUrl + moviePosterPath

            Glide.with(context)
                .load(image)
//                .error(R.mipmap.tvmovieposter)
                .into(holder.binding.moviePosterImageview)

            movieTitle.text = movies[position].title
            MovieReleaseDate.text = movies[position].release_date
        }
    }

    fun updateMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}