package com.gads.tunde.eduzone.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gads.tunde.eduzone.model.Course

@Entity(tableName = "courses_table")
data class DatabaseCourse constructor(
    @PrimaryKey
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
)

fun List<DatabaseCourse>.asDomainModel(): List<Course> {
    return map {
        Course(
            id = it.id,
            title = it.title,
            description = it.description,
            price = it.price,
            image = it.image,
            url = it.url,
            instructor = it.instructor,
            instructorTitle = it.instructorTitle,
            instructorImage = it.instructorImage,
            isBookmarked = it.isBookmarked
        )
    }
}