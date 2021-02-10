package com.example.teststream.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val location: String,
    val description: String,
    val image: String
) : Parcelable