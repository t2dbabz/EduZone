package com.gads.tunde.eduzone.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gads.tunde.eduzone.database.CoursesDatabase
import com.gads.tunde.eduzone.database.DatabaseCourse
import com.gads.tunde.eduzone.repository.CoursesRepository
import kotlinx.coroutines.launch

class DetailViewModel(application: Application): AndroidViewModel(application) {
    private val databaseCourse = CoursesDatabase.getInstance(application)
    private val coursesRepository = CoursesRepository(databaseCourse)

    fun insertCourse(course: DatabaseCourse) {
        viewModelScope.launch {
            coursesRepository.insertCourse(course)
        }
    }

    fun updateCourse(course: DatabaseCourse) {
        viewModelScope.launch {
            coursesRepository.updateCourse(course)

        }
    }

}