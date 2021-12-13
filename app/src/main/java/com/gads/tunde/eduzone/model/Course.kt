package com.gads.tunde.eduzone.model

import java.io.Serializable

data class Course(
    val id : Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val url: String,
    val instructor: String,
    val instructorTitle: String,
    val instructorImage: String,
    var isBookmarked: Boolean = false
) : Serializable
