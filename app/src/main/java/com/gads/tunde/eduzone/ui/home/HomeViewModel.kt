package com.gads.tunde.eduzone.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.gads.tunde.eduzone.database.CoursesDatabase
import com.gads.tunde.eduzone.network.NetworkCourse
import com.gads.tunde.eduzone.network.UdemyApi
import com.gads.tunde.eduzone.repository.CoursesRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val databaseCourse = CoursesDatabase.getInstance(application)
    private val coursesRepository = CoursesRepository(databaseCourse)

    init {
        viewModelScope.launch{
            Log.i("HomeViewModel", "viewmodel init called")
            coursesRepository.refreshCourses()
        }
    }

    val coursesList = coursesRepository.courses


}