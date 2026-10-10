package com.ahona.studentmanagerpro24.data.source

import com.ahona.studentmanagerpro24.data.local.StudentDao
import com.ahona.studentmanagerpro24.data.model.Student
import kotlinx.coroutines.flow.Flow

class StudentRoomDataSource (
    private val studentDao: StudentDao
){

    fun getStudents(): Flow<List<Student>> {
        return studentDao.getStudents()
    }

    suspend  fun addStudent(student: Student) {
        studentDao.insertStudent(student)
    }

    suspend  fun updateStudent(student: Student) {
        studentDao.updateStudent(student)
    }


//    fun deleteStudent(studentId: Int): Result<Unit> {
//
//        val student = studentDao.getStudents()
//            .find { it.id == studentId }
//
//        return if (student != null) {
//            studentDao.deleteStudent(student)
//            Result.success(Unit)
//        } else {
//            Result.failure(Exception("Student not found"))
//        }
//    }



//
//    fun deleteStudent(studentId: Int): Result<Unit> {
//        return Result.failure(
//            Exception("Delete requires coroutine support")
//        )
//    }

    suspend fun deleteStudent(studentId: Int): Result<Unit> {
        val student = studentDao.findStudentById(studentId)
            ?: return Result.failure(Exception("Student not found"))

        studentDao.deleteStudent(student)
        return Result.success(Unit)
    }


}