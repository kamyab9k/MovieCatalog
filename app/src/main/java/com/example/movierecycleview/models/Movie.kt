package com.example.movierecycleview.models

import android.os.Parcelable
import android.service.quicksettings.Tile
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(

    @SerializedName("id")
    val id: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("poster_patch")
    val poster_patch: String?,

    @SerializedName("release_date")
    val release_date: String,
) : Parcelable {
    constructor() : this("", "", "", "")
}