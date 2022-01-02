package com.gads.tunde.eduzone.ui.categories

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gads.tunde.eduzone.database.CoursesDatabase
import com.gads.tunde.eduzone.model.Course
import com.gads.tunde.eduzone.network.asDomainModel
import com.gads.tunde.eduzone.repository.CoursesRepository
import com.gads.tunde.eduzone.ui.home.HomeViewModel.NetworkResponseStatus
import kotlinx.coroutines.launch
import java.lang.Exception

class CategoriesViewModel(application: Application): AndroidViewModel(application) {
    private val courseDatabase = CoursesDatabase.getInstance(application)
    private val coursesRepository = CoursesRepository(courseDatabase)


    private val _getStartedCategoryStatus = MutableLiveData<NetworkResponseStatus>()
    val getStartedCategoryStatus : LiveData<NetworkResponseStatus>
        get() = _getStartedCategoryStatus

    private val _getStartedCategory = MutableLiveData<List<Course>>()
    val getStartedCategory : LiveData<List<Course>>
        get() = _getStartedCategory

    private val _featuredCategoryStatus = MutableLiveData<NetworkResponseStatus>()
    val featuredCategoryStatus : LiveData<NetworkResponseStatus>
        get() = _featuredCategoryStatus

    private val _featuredCategory = MutableLiveData<List<Course>>()
    val featuredCategory : LiveData<List<Course>>
        get() = _featuredCategory

    private val _popularCategoryStatus = MutableLiveData<NetworkResponseStatus>()
    val popularCategoryStatus : LiveData<NetworkResponseStatus>
        get() = _popularCategoryStatus

    private val _popularCategory = MutableLiveData<List<Course>>()
    val popularCategory : LiveData<List<Course>>
        get() = _popularCategory




    fun getStartedCourses(category: String) {
        viewModelScope.launch {
            try {
                _getStartedCategoryStatus.value = NetworkResponseStatus.LOADING
                val resultList = coursesRepository.getStarterCoursesCategory(category).asDomainModel()
                if (resultList.isNotEmpty()) {
                    _getStartedCategory.value = resultList
                    _getStartedCategoryStatus.value = NetworkResponseStatus.DONE
                }

            } catch (e: Exception) {
                _getStartedCategoryStatus.value = NetworkResponseStatus.ERROR
            }
        }

    }

    fun getFeaturedCourses(category: String) {
        viewModelScope.launch {
            try {
                _featuredCategoryStatus.value = NetworkResponseStatus.LOADING
                val resultList = coursesRepository.getFeaturedCoursesCategory(category).asDomainModel()
                if (resultList.isNotEmpty()) {
                    _featuredCategory.value = resultList
                    _featuredCategoryStatus.value = NetworkResponseStatus.DONE
                }

            } catch (e: Exception) {
                _featuredCategoryStatus.value = NetworkResponseStatus.ERROR
            }
        }
    }

    fun getPopularCourses(category: String) {
        viewModelScope.launch {
            try {
                _popularCategoryStatus.value = NetworkResponseStatus.LOADING
                val resultList = coursesRepository.getPopularCoursesCategory(category).asDomainModel()
                if (resultList.isNotEmpty()) {
                    _popularCategory.value = resultList
                    _popularCategoryStatus.value = NetworkResponseStatus.DONE
                }

            } catch (e: Exception) {
                _popularCategoryStatus.value = NetworkResponseStatus.ERROR
            }
        }
    }
}