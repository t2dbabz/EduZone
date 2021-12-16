package com.gads.tunde.eduzone.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gads.tunde.eduzone.database.CoursesDatabase
import com.gads.tunde.eduzone.model.Course
import com.gads.tunde.eduzone.network.asDomainModel
import com.gads.tunde.eduzone.repository.CoursesRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchViewModel(application: Application) :AndroidViewModel(application) {

    private val databaseCourse = CoursesDatabase.getInstance(application)
    private val coursesRepository = CoursesRepository(databaseCourse)

    private val _searchResultList = MutableLiveData<List<Course>>()
    val searchResultList : LiveData<List<Course>>
    get() = _searchResultList

    fun searchCourses(searchTerm: String) {

        viewModelScope.launch {
            try {

                val searchList = coursesRepository.searchCourse(searchTerm).asDomainModel()

                _searchResultList.value = searchList


            } catch (e : Exception) {

            }
        }

    }
}