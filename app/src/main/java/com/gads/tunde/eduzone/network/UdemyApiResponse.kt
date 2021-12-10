package com.gads.tunde.eduzone.network

import com.gads.tunde.eduzone.model.Course

data class UdemyApiResponse(
    val results: List<Course>,
    val count: Int,
    val next: String,
)