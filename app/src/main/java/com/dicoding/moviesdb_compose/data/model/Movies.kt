package com.dicoding.moviesdb_compose.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
    val id: Int,
    val name: String,
    val description: String,
    val photo: String,
    val releaseDate: String,
    val photoCover: String
): Parcelable
