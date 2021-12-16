package com.gads.tunde.eduzone.ui.bookmark

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gads.tunde.eduzone.database.CoursesDatabase
import com.gads.tunde.eduzone.repository.CoursesRepository

class BookmarkViewModel(application: Application): AndroidViewModel(application) {
    private val databaseCourse = CoursesDatabase.getInstance(application)
    private val coursesRepository = CoursesRepository(databaseCourse)

    val bookmarkedCourses = coursesRepository.bookmarkedCourses
}