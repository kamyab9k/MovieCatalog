package com.example.movierecycleview

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.target.Target
import com.example.movierecycleview.databinding.MovieItemBinding
import com.example.movierecycleview.models.Movie


class MovieAdaptor(
    private val context: Context,
    private val movies: List<Movie>,
) : RecyclerView.Adapter<MovieAdaptor.MovieViewHolder>() {

    inner class MovieViewHolder(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

//    @GlideModule
//    class MyAppGlideModule : AppGlideModule() {
//        // leave empty for now
//    }

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
            val movie = movies[position]
            val imagebase =
                "https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg\n" + movie.poster_patch
            Glide.with(context)
                .load(imagebase)
                .error(R.mipmap.tvmovieposter)
                .centerCrop()
                .into(holder.binding.moviePoster)
            movies[position]
            movieTitle.text = movies[position].title
            MovieReleaseDate.text = movies[position].release_date
        }
    }
}