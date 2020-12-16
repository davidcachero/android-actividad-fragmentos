package com.example.tarea3davidcacherofragments.database

import android.content.Context
import android.os.AsyncTask

class DataRepository(context: Context) {
    private val subjectsDao: SubjectsDao? = AppDatabase.getInstance(context)?.subjectsDao()
    private val studentsDao: StudentsDao? = AppDatabase.getInstance(context)?.studentsDao()
    private val teachersDao: TeachersDao? = AppDatabase.getInstance(context)?.teachersDao()
    private val subjectsStudentsDao: SubjectsStudentsDao? =
        AppDatabase.getInstance(context)?.subjectsStudentsDao()
    private val subjectsTeachersDao: SubjectsTeachersDao? =
        AppDatabase.getInstance(context)?.subjectsTeachersDao()

    fun AsignAlum(subjectStudent: SubjectStudent): Int {
        if (subjectsDao != null && studentsDao != null && subjectsStudentsDao != null) {
            return InsertSubjectStudentAsyncTask(
                subjectsDao,
                studentsDao,
                subjectsStudentsDao
            ).execute(subjectStudent).get()
        }
        return -1
    }

    fun getAsigAlum(AsigAlum: String): List<SubjectStudent> {
        return GetAsigAlumOne(subjectsStudentsDao!!, AsigAlum).execute().get()
    }

    fun getAsigProf(AsigProf: String): List<SubjectTeacher> {
        return GetAsigProfOne(subjectsTeachersDao!!, AsigProf).execute().get()
    }

    private class InsertSubjectStudentAsyncTask(
        private val subjectsDao: SubjectsDao,
        private val studentsDao: StudentsDao,
        private val subjectsStudentsDao: SubjectsStudentsDao
    ) : AsyncTask<SubjectStudent, Void, Int>() {
        override fun doInBackground(vararg subjectStudent: SubjectStudent?): Int {
            try {
                for (asig in subjectStudent) {
                    if (asig != null) {
                        subjectsDao.insertAll(asig.subjects)
                        studentsDao.insertAll(asig.students)
                        for (student in asig.students) {
                            subjectsStudentsDao.insert(
                                AsigAlumCrossRef(
                                    asig.subjects.nombre,
                                    student.AlumnId
                                )
                            )
                        }
                    }
                }

                return 0
            } catch (exception: Exception) {
                return -1
            }
        }
    }

    fun AignProf(subjectTeacher: SubjectTeacher): Int {
        if (subjectsDao != null && teachersDao != null && subjectsTeachersDao != null) {
            return InsertSubjectTeacherAsyncTask(
                subjectsDao, teachersDao, subjectsTeachersDao
            ).execute(subjectTeacher).get()
        }
        return -1
    }

    private class InsertSubjectTeacherAsyncTask(
        private val subjectsDao: SubjectsDao,
        private val teachersDao: TeachersDao,
        private val subjectsTeachersDao: SubjectsTeachersDao
    ) : AsyncTask<SubjectTeacher, Void, Int>() {
        override fun doInBackground(vararg subjectTeacher: SubjectTeacher?): Int {
            try {
                for (sub in subjectTeacher) {
                    if (sub != null) {
                        subjectsDao.insertAll(sub.subjects)
                        teachersDao.insertAll(sub.teachers)
                        for (teacher in sub.teachers) {
                            subjectsTeachersDao.insert(
                                AsigProfCrossRef(
                                    sub.subjects.nombre,
                                    teacher.ProfId
                                )
                            )
                        }
                    }
                }

                return 0
            } catch (exception: Exception) {
                return -1
            }
        }
    }

    private class getStudents(private val studentsDao: StudentsDao) :
        AsyncTask<Void, Void, Int>() {
        override fun doInBackground(vararg params: Void?): Int {
            return studentsDao.getAlum()
        }
    }

    private class getSubject(private val subjectsDao: SubjectsDao) :
        AsyncTask<Void, Void, Int>() {
        override fun doInBackground(vararg params: Void?): Int {
            return subjectsDao.getCount()
        }
    }

    private class getTeacher(private val teachersDao: TeachersDao) :
        AsyncTask<Void, Void, Int>() {
        override fun doInBackground(vararg params: Void?): Int {
            return teachersDao.getCount()
        }
    }

    private class getProf(private val subjectsTeachersDao: SubjectsTeachersDao) :
        AsyncTask<Void, Void, List<SubjectTeacher>>() {
        override fun doInBackground(vararg params: Void?): List<SubjectTeacher> {
            return subjectsTeachersDao.getAsig()
        }
    }

    private class GetAsigProfOne(
        private val subjectsTeachersDao: SubjectsTeachersDao,
        private val name: String
    ) : AsyncTask<Void, Void, List<SubjectTeacher>>() {
        override fun doInBackground(vararg params: Void?): List<SubjectTeacher> {
            return subjectsTeachersDao.getAsigOne(name)
        }
    }

    private class getAsigAlum(private val subjectsStudentsDao: SubjectsStudentsDao) :
        AsyncTask<Void, Void, List<SubjectStudent>>() {
        override fun doInBackground(vararg params: Void?): List<SubjectStudent> {
            return subjectsStudentsDao.getAsig()
        }
    }

    private class GetAsigAlumOne(
        private val subjectsStudentsDao: SubjectsStudentsDao,
        private val name: String
    ) : AsyncTask<Void, Void, List<SubjectStudent>>() {
        override fun doInBackground(vararg params: Void?): List<SubjectStudent> {
            return subjectsStudentsDao.getAsigOne(name)
        }
    }

    private class getsubj(private val subjectsDao: SubjectsDao) :
        AsyncTask<Void, Void, List<Subjects>>() {
        override fun doInBackground(vararg params: Void?): List<Subjects> {
            return subjectsDao.getAsig()
        }
    }

    fun getAsign(): List<Subjects> {
        var asign = getsubj(subjectsDao!!).execute().get()
        return asign
    }

    fun getAsig(): Int {
        var asig = getSubject(subjectsDao!!).execute().get()
        return asig
    }

    fun getTeachers(): Int {
        var prof = getTeacher(teachersDao!!).execute().get()
        return prof
    }

    fun getStudent(): Int {
        var alum = getStudents(studentsDao!!).execute().get()
        return alum
    }
}
