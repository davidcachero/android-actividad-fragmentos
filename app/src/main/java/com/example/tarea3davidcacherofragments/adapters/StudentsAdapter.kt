package com.example.tarea3davidcacherofragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tarea3davidcacherofragments.R
import com.example.tarea3davidcacherofragments.database.Students

class StudentsAdapter(var items: ArrayList<Students>, private val listener: (Students) -> Unit) :
    RecyclerView.Adapter<StudentsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        val viewHolder = ViewHolder(v)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(items[position])
        holder.itemView.setOnClickListener { listener(items[position]) }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Nombre: TextView
        val Apellidos: TextView

        init {
            Nombre = itemView.findViewById<TextView>(R.id.textViewName)
            Apellidos = itemView.findViewById<TextView>(R.id.textViewSurname)
        }

        fun bindItems(students: Students) {
            Nombre.text = students.NombreAlumn
            Apellidos.text = students.ApellidoAlumn
        }
    }

}