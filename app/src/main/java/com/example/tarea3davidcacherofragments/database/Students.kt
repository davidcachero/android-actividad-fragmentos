package com.example.tarea3davidcacherofragments.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Students(
    @PrimaryKey var AlumnId: Int,
    @ColumnInfo var NombreAlumn: String,
    @ColumnInfo var ApellidoAlumn: String
)