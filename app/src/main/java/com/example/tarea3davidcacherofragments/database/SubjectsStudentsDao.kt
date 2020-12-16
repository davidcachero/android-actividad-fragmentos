package com.example.tarea3davidcacherofragments.database

import androidx.room.*

@Dao
interface SubjectsStudentsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(join: AsigAlumCrossRef)

    @Transaction
    @Query("select * from Subjects")
    fun getAsig(): List<SubjectStudent>

    @Transaction
    @Query("select * from Subjects where nombre = :nombre")
    fun getAsigOne(nombre: String): List<SubjectStudent>
}