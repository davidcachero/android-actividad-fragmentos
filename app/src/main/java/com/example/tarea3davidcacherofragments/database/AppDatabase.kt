package com.example.tarea3davidcacherofragments.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Subjects::class, Teachers::class, Students::class, AsigAlumCrossRef::class, AsigProfCrossRef::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun subjectsDao(): SubjectsDao
    abstract fun teachersDao(): TeachersDao
    abstract fun studentsDao(): StudentsDao
    abstract fun subjectsTeachersDao(): SubjectsTeachersDao
    abstract fun subjectsStudentsDao(): SubjectsStudentsDao

    companion object {
        private const val DATABASE_NAME = "BBDD_data"

        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            INSTANCE
                ?: synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }
            return INSTANCE
        }
    }
}