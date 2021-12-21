package com.gads.tunde.eduzone.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.gads.tunde.eduzone.database.CoursesDatabase
import com.gads.tunde.eduzone.model.Course
import com.gads.tunde.eduzone.network.asDomainModel
import com.gads.tunde.eduzone.repository.CoursesRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    enum class NetworkResponseStatus {LOADING, DONE, ERROR}

    private val databaseCourse = CoursesDatabase.getInstance(application)
    private val coursesRepository = CoursesRepository(databaseCourse)

    private val _featuredCourseStatus = MutableLiveData<NetworkResponseStatus>()
    val featuredCourseStatus : LiveData<NetworkResponseStatus>
    get() = _featuredCourseStatus

    private val _featuredCourses = MutableLiveData<List<Course>>()
    val featuredCourses : LiveData<List<Course>>
    get() = _featuredCourses

    private val _topCoursesStatus = MutableLiveData<NetworkResponseStatus>()
    val topCourseStatus : LiveData<NetworkResponseStatus>
        get() = _topCoursesStatus

    private val _topCourses = MutableLiveData<List<Course>>()
    val topCourses : LiveData<List<Course>>
        get() = _topCourses


    private val _newCoursesStatus = MutableLiveData<NetworkResponseStatus>()
    val newCourseStatus : LiveData<NetworkResponseStatus>
        get() = _newCoursesStatus

    private val _newCourses = MutableLiveData<List<Course>>()
    val newCourses : LiveData<List<Course>>
        get() = _newCourses



    val categoryListOne = listOf("Business", "Development", "IT & Software", "Lifestyle", "Design",
        "Teaching & Academics", "Photography & Video")
    val categoryListTwo = listOf("Personal Development", "Office Productivity", "Finance & Accounting", "Marketing", "Music")


    init {
        getFeaturedCourses()
        getTopCourses()
        getNewCourses()
    }



    val coursesList = coursesRepository.courses

    private fun refreshCourses() {
        viewModelScope.launch{
            coursesRepository.refreshCourses()
        }
    }

    fun getFeaturedCourses() {
        viewModelScope.launch {
            try {
                _featuredCourseStatus.value = NetworkResponseStatus.LOADING
                val resultList = coursesRepository.getFeaturedCourses().asDomainModel()
                if (resultList.isNotEmpty()){
                    _featuredCourses.value = resultList
                    _featuredCourseStatus.value = NetworkResponseStatus.DONE
                }
            } catch (e: Exception) {
                _featuredCourseStatus.value = NetworkResponseStatus.ERROR
            }
        }
    }

    fun getTopCourses() {
        viewModelScope.launch {
            try {
                _topCoursesStatus.value = NetworkResponseStatus.LOADING
                val resultList = coursesRepository.getTopCourses().asDomainModel()
                if(resultList.isNotEmpty()) {
                    _topCourses.value = resultList
                    _topCoursesStatus.value = NetworkResponseStatus.DONE
                }
            } catch (e : Exception) {
                _topCoursesStatus.value =NetworkResponseStatus.ERROR

            }
        }
    }

    fun getNewCourses() {
        viewModelScope.launch {
            try {
                _newCoursesStatus.value = NetworkResponseStatus.LOADING
                val resultList = coursesRepository.getNewCourses().asDomainModel()
                if (resultList.isNotEmpty()) {
                    _newCourses.value = resultList
                    _newCoursesStatus.value = NetworkResponseStatus.DONE
                }
            } catch (e : Exception) {
                _newCoursesStatus.value = NetworkResponseStatus.ERROR
            }
        }
    }


}