package com.example.tarea3davidcacherofragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.tarea3davidcacherofragments.R
import com.example.tarea3davidcacherofragments.database.Students

class StudentsFragments : Fragment() {
    var textViewNombre: TextView? = null
    var textViewApellido: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.students_selected, container, false)
        textViewNombre = v.findViewById<View>(R.id.textViewTeacherName) as TextView
        textViewApellido = v.findViewById<View>(R.id.textViewTeacherSurname) as TextView
        return v
    }

    fun updateData(item: Students?) {
        if (item != null) {
            textViewNombre!!.text = item.NombreAlumn
            textViewApellido!!.text = item.ApellidoAlumn
        }

    }
}