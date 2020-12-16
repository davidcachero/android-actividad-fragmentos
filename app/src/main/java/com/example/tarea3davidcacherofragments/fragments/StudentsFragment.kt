package com.example.tarea3davidcacherofragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tarea3davidcacherofragments.R
import com.example.tarea3davidcacherofragments.adapters.StudentsAdapter
import com.example.tarea3davidcacherofragments.database.DataRepository
import com.example.tarea3davidcacherofragments.database.Students

class StudentsFragment : Fragment() {
    var activityListener: View.OnClickListener? = null
    var itemRecyclerView: RecyclerView? = null
    var item: Students? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.students_list, container, false)
        itemRecyclerView = v.findViewById<View>(R.id.recyclerViewBlue) as RecyclerView
        return v
    }

    fun recibirDatos(selected: String) {
        var context = context!!.applicationContext
        var items = ArrayList<Students>()
        var dataRepository = DataRepository(context)
        var alum = dataRepository.getAsigAlum(selected)
        val asig = alum.component1().students.size.toString()
        for (i in 0..asig.toInt() - 1) {
            val AlumNombre = alum.component1().students[i].NombreAlumn.toString()
            val AlumApellidos = alum.component1().students[i].ApellidoAlumn.toString()
            items.add(
                Students(
                    0, AlumNombre, AlumApellidos
                )
            )
        }

        var adapter = StudentsAdapter(items) { item ->
            this.item = item
            if (activityListener != null) {
                activityListener!!.onClick(view)
            }
        }

        itemRecyclerView!!.setAdapter(adapter)
        itemRecyclerView!!.setLayoutManager(
            LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false
            )
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = StudentsFragment().apply {
        }
    }
}