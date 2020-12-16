package com.example.tarea3davidcacherofragments.database

import androidx.room.*

@Dao
interface SubjectsTeachersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(join: AsigProfCrossRef)

    @Transaction
    @Query("select * from Subjects")
    fun getAsig(): List<SubjectTeacher>

    @Transaction
    @Query("select * from Subjects where nombre = :nombre")
    fun getAsigOne(nombre: String): List<SubjectTeacher>
}
