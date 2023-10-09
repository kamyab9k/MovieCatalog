package com.example.movieCatalog

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieCatalog.models.Movie
import com.example.movieCatalog.databinding.MovieItemBinding


class MovieAdaptor(
    private val context: Context,
) : RecyclerView.Adapter<MovieAdaptor.MovieViewHolder>() {

//    private var onClickListener: OnClickListener? = null
    private var moviesList: List<Movie> = emptyList()

    inner class MovieViewHolder(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.apply {
//            val item = moviesList[position]
            val moviePosterPath = moviesList[position].poster_path
            val imageBaseUrl = "https://image.tmdb.org/t/p/w500/"
            val image = imageBaseUrl + moviePosterPath

            Glide.with(context)
                .load(image)
                .into(holder.binding.moviePosterImageview)

            movieTitle.text = moviesList[position].title
            MovieReleaseDate.text = moviesList[position].release_date
            Rating.text= moviesList[position].vote_average
        }
    }

    fun updateMovies(newMovies: List<Movie>) {
        moviesList = newMovies
        notifyDataSetChanged()
    }
}