package com.example.tarea3davidcacherofragments.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TeachersDao {
    @Query("select * from teachers")
    fun getAll(): List<Teachers>

    @Query("select count(*) from teachers")
    fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(teachers: List<Teachers>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg teachers: Teachers)


}