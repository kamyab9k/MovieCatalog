package com.example.movieCatalog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieCatalog.models.Movie
import com.example.movieCatalog.databinding.MovieItemBinding


class MovieAdapter(
    private val context: Context,
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

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
            LikeButton.setOnClickListener {

                moviesList[position].isLiked = moviesList[position].isLiked?.let { it1 ->
                    toggleLikeState(
                        it1
                    )
                }

                if (moviesList[position].isLiked == "true") {
                    LikeButton.setImageResource(R.drawable.red_favorite)
                    Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show()
                } else {
                    LikeButton.setImageResource(R.drawable.baseline_favorite_24)
                }


            }
            val moviePosterPath = moviesList[position].poster_path
            val imageBaseUrl = "https://image.tmdb.org/t/p/w500/"
            val image = imageBaseUrl + moviePosterPath

            Glide.with(context)
                .load(image)
                .into(holder.binding.moviePosterImageview)

            movieTitle.text = moviesList[position].title
            MovieReleaseDate.text = moviesList[position].release_date
            Rating.text = moviesList[position].vote_average
        }
        holder.itemView.setOnClickListener {
            val movie = moviesList[position]

            val detailFragment = MovieDetailFragment()
            val args = Bundle()
            args.putParcelable("movieKey", movie)
            detailFragment.arguments = args

            val transaction =
                (context as AppCompatActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, detailFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }

    private fun toggleLikeState(currentState: String): String {
        return if (currentState == "true") {
            "false"
        } else {
            "true"
        }
    }
    fun updateMovies(newMovies: List<Movie>) {
        moviesList = newMovies
        notifyDataSetChanged()
    }
}