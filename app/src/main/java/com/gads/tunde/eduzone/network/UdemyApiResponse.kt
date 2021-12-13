package com.gads.tunde.eduzone.network

import com.gads.tunde.eduzone.database.DatabaseCourse
import com.gads.tunde.eduzone.model.Course

data class UdemyApiResponse(
    val results: List<NetworkCourse>,
    val count: Int,
    val next: String,
)




fun UdemyApiResponse.asDomainModel() : List<Course> {
    return results.map {
        Course(
            id = it.courseId,
            title = it.courseTitle,
            description = it.courseDescription,
            price = it.coursePrice,
            image = it.courseImageLarge,
            url = it.courseUrl,
            instructor = it.visible_instructors[0].display_name,
            instructorTitle = it.visible_instructors[0].job_title,
            instructorImage = it.visible_instructors[0].image_100x100,

        )
    }
}


fun UdemyApiResponse.asDatabaseModel() : Array<DatabaseCourse> {
    return results.map {
        DatabaseCourse(
            id = it.courseId,
            title = it.courseTitle,
            description = it.courseDescription,
            price = it.coursePrice,
            image = it.courseImageLarge,
            url = it.courseUrl,
            instructor = it.visible_instructors[0].display_name,
            instructorTitle = it.visible_instructors[0].job_title,
            instructorImage = it.visible_instructors[0].image_100x100,
        )
    }.toTypedArray()
}

