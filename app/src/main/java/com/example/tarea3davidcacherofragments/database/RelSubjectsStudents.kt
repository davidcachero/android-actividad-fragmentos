package com.example.tarea3davidcacherofragments.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

class SubjectStudent(
    @Embedded
    var subjects: Subjects,
    @Relation(
        parentColumn = "nombre",
        entity = Students::class,
        entityColumn = "AlumnId",
        associateBy = Junction(
            value = AsigAlumCrossRef::class,
            parentColumn = "nombre",
            entityColumn = "AlumnId"
        )
    )

    var students: List<Students>
)

@Entity(primaryKeys = ["nombre", "AlumnId"])
data class AsigAlumCrossRef(
    val nombre: String,
    val AlumnId: Int
)