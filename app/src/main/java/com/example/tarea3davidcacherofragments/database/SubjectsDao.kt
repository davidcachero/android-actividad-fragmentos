package com.example.tarea3davidcacherofragments.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SubjectsDao {
    @Query("select count(*) from subjects")
    fun getCount(): Int

    @Query("select * from subjects")
    fun getAsig(): List<Subjects>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg subjects: Subjects)
}