package com.example.tarea3davidcacherofragments.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StudentsDao {
    @Query("select * from students")
    fun getAll(): List<Students>

    @Query("select count(*) from students")
    fun getAlum(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(students: List<Students>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg students: Students)
}