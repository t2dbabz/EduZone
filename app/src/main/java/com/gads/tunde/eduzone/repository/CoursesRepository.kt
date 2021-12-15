package com.gads.tunde.eduzone.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.gads.tunde.eduzone.database.CoursesDatabase
import com.gads.tunde.eduzone.database.DatabaseCourse
import com.gads.tunde.eduzone.database.asDomainModel
import com.gads.tunde.eduzone.model.Course
import com.gads.tunde.eduzone.network.UdemyApi
import com.gads.tunde.eduzone.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoursesRepository (private val database: CoursesDatabase){

    val courses : LiveData<List<Course>> = Transformations.map(database.courseDao.getCourses()){
        it.asDomainModel()
    }


    suspend fun refreshCourses() {
        withContext(Dispatchers.IO){
            try {
                val coursesList = UdemyApi.retrofitService.getCourses()
                Log.i("Repo class", coursesList.results.toString())
                database.courseDao.insertAll(*coursesList.asDatabaseModel())
            } catch (e : Exception) {
                Log.i("Failure", e.message.toString())
            }

        }
    }

    suspend fun updateCourse(course: DatabaseCourse){
        database.courseDao.updateCourse(course)
    }

}