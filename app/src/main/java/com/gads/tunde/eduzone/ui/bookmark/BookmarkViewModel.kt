package com.gads.tunde.eduzone.ui.bookmark

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.gads.tunde.eduzone.database.CoursesDatabase
import com.gads.tunde.eduzone.database.DatabaseCourse
import com.gads.tunde.eduzone.repository.CoursesRepository
import kotlinx.coroutines.launch

class BookmarkViewModel(application: Application): AndroidViewModel(application) {
    private val databaseCourse = CoursesDatabase.getInstance(application)
    private val coursesRepository = CoursesRepository(databaseCourse)

    val bookmarkedCourses = coursesRepository.bookmarkedCourses


    fun deleteCourse(course: DatabaseCourse) {
        viewModelScope.launch {
            coursesRepository.deleteCourse(course)
        }
    }

    fun insertCourse(course: DatabaseCourse) {
        viewModelScope.launch {
            coursesRepository.insertCourse(course)
        }
    }
 }