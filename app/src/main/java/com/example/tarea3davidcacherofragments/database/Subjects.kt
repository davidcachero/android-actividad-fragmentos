package com.example.tarea3davidcacherofragments.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subjects(
    @PrimaryKey val nombre: String
)