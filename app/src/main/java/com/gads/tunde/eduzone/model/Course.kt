package com.gads.tunde.eduzone.model

import com.squareup.moshi.Json
import java.io.Serializable

data class Course(
    @Json(name = "id")
    val courseId: Int,

    @Json(name = "title")
    val courseTitle: String,

    @Json(name = "headline")
    val courseDescription: String,

    @Json(name = "price")
    val coursePrice: String,

    @Json(name = "url")
    val courseUrl: String,

    @Json(name = "image_125_H")
    val courseImageSmall: String,

    @Json(name = "image_240x135")
    val courseImageMid: String,

    @Json(name = "image_480x270")
    val courseImageLarge: String,

    @Json(name ="is_paid")
    val isPaid: Boolean,

    val price_detail: PriceDetail? = null,

    val visible_instructors: List<VisibleInstructor>
) : Serializable