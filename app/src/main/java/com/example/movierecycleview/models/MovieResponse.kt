package com.example.movierecycleview.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@kotlinx.android.parcel.Parcelize
data class MovieResponse(
   @SerializedName("results")
   var movies: List<Movie>,
) : Parcelable {
    constructor() : this(mutableListOf())
}