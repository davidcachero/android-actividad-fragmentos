package com.example.tarea3davidcacherofragments

import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.tarea3davidcacherofragments.fragments.TeacherFragments
import org.json.JSONArray
import com.example.tarea3davidcacherofragments.database.*
import com.example.tarea3davidcacherofragments.fragments.StudentsFragment
import com.example.tarea3davidcacherofragments.fragments.StudentsFragments

class MainActivity : AppCompatActivity() {
    lateinit var frameLayout: String
    var frameLayoutFragment: FrameLayout? = null
    var frameLayoutLista: FrameLayout? = null
    var frameLayoutFicha: FrameLayout? = null
    var frameLayoutProf: FrameLayout? = null
    var studentsFragments: StudentsFragments? = null
    var teacherFragments: TeacherFragments? = null
    var studentsFragment: StudentsFragment? = null
    var segundoFragmentActivo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var dataRepository = DataRepository(this)
        var codAlumn = dataRepository.getStudent()
        var codAsig = dataRepository.getAsig()
        var codProf = dataRepository.getTeachers()
        frameLayoutFragment = findViewById(R.id.frameLayoutAlum)
        frameLayoutLista = findViewById(R.id.frameLayoutFragmentLista)
        frameLayoutFicha = findViewById(R.id.frameLayoutFragmentLFicha)
        frameLayoutProf = findViewById(R.id.frameLayoutProf)
        if (codAlumn.toString().toInt() == 0
            && codAsig.toString().toInt() == 0
            && codProf.toString().toInt() == 0
        ) {

            rellenarDatos()

        }

        var dataRep = DataRepository(this)
        var asign = dataRep.getAsign()
        var asig = ArrayList<String>()
        val codigoAsig = asign.component1().nombre.toString()
        val codAsign = asign.component2().nombre
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, asig)
        var arraySpinner = ArrayList<String>()
        var spinner = findViewById<Spinner>(R.id.spinner)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        asig.add(codigoAsig)
        asig.add(codAsign)
        spinner.adapter = adapter
        arraySpinner.add("Asignatura:")
        studentsFragment = StudentsFragment.newInstance()
        studentsFragment!!.activityListener = activityListener
        studentsFragments = StudentsFragments()
        teacherFragments = TeacherFragments.newInstance()
        frameLayout = spinner.selectedItem.toString()
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                frameLayout = parent.getItemAtPosition(position).toString()
                teacherFragments!!.onUpdateData(frameLayout)
                studentsFragment!!.recibirDatos(frameLayout)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        if (frameLayoutFragment == null) {
            fragmentTransaction.add(R.id.frameLayoutProf, teacherFragments!!)
            fragmentTransaction.add(R.id.frameLayoutFragmentLista, studentsFragment!!)
            fragmentTransaction.add(R.id.frameLayoutFragmentLFicha, studentsFragments!!)

        } else {

            fragmentTransaction.add(R.id.frameLayoutProf, teacherFragments!!)
            fragmentTransaction.add(R.id.frameLayoutAlum, studentsFragment!!)
        }

        fragmentTransaction.commit()
    }

    var activityListener = View.OnClickListener {
        if (frameLayoutFragment != null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayoutAlum, studentsFragments!!)
            fragmentTransaction.commit()
            fragmentManager.executePendingTransactions()
            segundoFragmentActivo = true
        }

        studentsFragments!!.updateData(studentsFragment!!.item)
    }

    override fun onBackPressed() {
        if (segundoFragmentActivo && frameLayoutFragment != null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayoutAlum, studentsFragment!!)
            fragmentTransaction.commit()
            fragmentManager.executePendingTransactions()
            segundoFragmentActivo = false

        } else {

            super.onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("cod", frameLayout)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        frameLayout = savedInstanceState.getString("cod").toString()
    }

    fun rellenarDatos() {
        var subjOne: Subjects? = null
        var SubjSecond: Subjects? = null
        var bf = BufferedReader(
            InputStreamReader(
                resources.openRawResource(R.raw.datos)
            )
        )

        var rd = bf.readLine()
        var json = JSONObject(rd)
        val asig: JSONArray = json.getJSONArray("asignaturas")
        for (i in 0 until asig.length()) {
            if (asig[i].toString().equals("BBDD")) {
                subjOne = Subjects(asig[i].toString())

            } else {

                SubjSecond = Subjects(asig[i].toString())
            }
        }

        var profAsig = ArrayList<Teachers>()
        var profAsigSecond = ArrayList<Teachers>()
        val prof: JSONArray = json.getJSONArray("profesores")
        for (i in 0 until prof.length()) {
            val prof = prof.getJSONObject(i)
            if (prof.getString("asignatura").equals("programacion")) {
                val PROG: Teachers = Teachers(
                    prof.getInt("codigo"),
                    prof.getString("nombre"),
                    prof.getString("apellido")
                )

                profAsigSecond.add(PROG)

            } else {

                val BBDD: Teachers = Teachers(
                    prof.getInt("codigo"),
                    prof.getString("nombre"),
                    prof.getString("apellido")
                )

                profAsig.add(BBDD)
            }
        }


        var alumnAsig = ArrayList<Students>()
        var alumnAsigSecond = ArrayList<Students>()
        val students: JSONArray = json.getJSONArray("alumnos")
        for (i in 0 until students.length()) {
            val alum = students.getJSONObject(i)
            var asig = alum.getJSONArray("Asignaturas")
            for (k in 0 until asig.length()) {
                if (asig[k].toString().equals("programacion")) {
                    val PROG = Students(
                        alum.getInt("codigo"),
                        alum.getString("nombre"),
                        alum.getString("apellido")
                    )

                    alumnAsigSecond.add(PROG)

                } else {

                    val BBDD = Students(
                        alum.getInt("codigo"),
                        alum.getString("nombre"),
                        alum.getString("apellido")
                    )

                    alumnAsig.add(BBDD)
                }
            }
        }

        var BBDD = SubjectTeacher(subjOne!!, profAsig)
        var AlumBBDD = SubjectStudent(subjOne!!, alumnAsig)
        var PROG = SubjectTeacher(SubjSecond!!, profAsigSecond)
        var AlumPROG = SubjectStudent(SubjSecond!!, alumnAsigSecond)
        var dataRepository = DataRepository(this)
        dataRepository.AsignAlum(AlumBBDD)
        dataRepository.AsignAlum(AlumPROG)
        dataRepository.AignProf(BBDD)
        dataRepository.AignProf(PROG)
        bf.close()
    }
}