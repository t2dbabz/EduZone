package com.gads.tunde.eduzone.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CourseDao {
    @Query("SELECT * FROM courses_table")
    fun getCourses() : LiveData<List<DatabaseCourse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg courses: DatabaseCourse)
}