package com.gads.tunde.eduzone.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gads.tunde.eduzone.Resource
import com.gads.tunde.eduzone.model.Course
import com.gads.tunde.eduzone.network.UdemyApi
import com.gads.tunde.eduzone.network.UdemyApiResponse
import com.gads.tunde.eduzone.network.UdemyApiService
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response : LiveData<String>
    get() = _response


    private val _courseList = MutableLiveData<List<Course>>()
    val courseList : LiveData<List<Course>>
    get() = _courseList

    init {
        getCourses()
    }

    private fun getCourses() {
        viewModelScope.launch {
            try {
                val courseList = UdemyApi.retrofitService.getCourses().results
                if (courseList.isNotEmpty()) {
                    _courseList.value = courseList
                }
            } catch (e: Exception) {
                _courseList.value = ArrayList()

            }
        }



    }

}