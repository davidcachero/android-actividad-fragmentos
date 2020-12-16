package com.example.tarea3davidcacherofragments.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Teachers(
    @PrimaryKey var ProfId: Int,
    @ColumnInfo var NombreProf: String,
    @ColumnInfo var ApellidoProf: String
)