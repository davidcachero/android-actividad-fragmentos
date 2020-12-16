package com.example.tarea3davidcacherofragments.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

data class SubjectTeacher(
    @Embedded var subjects: Subjects,
    @Relation(
        parentColumn = "nombre",
        entity = Teachers::class,
        entityColumn = "ProfId",
        associateBy = Junction(
            value = AsigProfCrossRef::class,
            parentColumn = "nombre",
            entityColumn = "ProfId"
        )
    )
    var teachers: List<Teachers>
)

@Entity(primaryKeys = ["nombre", "ProfId"])
data class AsigProfCrossRef(
    val nombre: String,
    val ProfId: Int
)