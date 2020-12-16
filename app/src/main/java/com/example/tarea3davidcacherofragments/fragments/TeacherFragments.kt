package com.example.tarea3davidcacherofragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.tarea3davidcacherofragments.R
import com.example.tarea3davidcacherofragments.database.DataRepository

class TeacherFragments : Fragment() {
    var textViewName: TextView? = null
    var textViewSubname: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.teacher, container, false)
        textViewName = v.findViewById<TextView>(R.id.textViewTeacherName)
        textViewSubname = v.findViewById<TextView>(R.id.textViewTeacherSurname)
        return v
    }

    fun onUpdateData(subject: String) {
        var context = context!!.applicationContext
        var dataRepository = DataRepository(context)
        var alum = dataRepository.getAsigProf(subject)
        for (i in 0..20) {
            var AlumNombre = alum.component1().teachers[0].NombreProf.toString()
            var AlumApellidos = alum.component1().teachers[0].ApellidoProf.toString()
            textViewName!!.text = AlumNombre + " " + AlumApellidos
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = TeacherFragments().apply {}
    }
}