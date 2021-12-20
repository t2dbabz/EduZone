package com.gads.tunde.eduzone.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CourseDao {
    @Query("SELECT * FROM courses_table")
    fun getCourses() : LiveData<List<DatabaseCourse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg courses: DatabaseCourse)

    @Update
    suspend fun updateCourse(course: DatabaseCourse)

    @Query(" SELECT * FROM courses_table WHERE isBookmarked = 1 ")
    fun getBookmarkedCourses() : LiveData<List<DatabaseCourse>>

    @Delete
    suspend fun deleteCourse(course: DatabaseCourse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: DatabaseCourse)


}