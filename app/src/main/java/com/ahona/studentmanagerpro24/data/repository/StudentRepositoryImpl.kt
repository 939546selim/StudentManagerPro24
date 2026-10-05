package com.ahona.studentmanagerpro24.data.repository

import com.ahona.studentmanagerpro24.data.model.Student
import com.ahona.studentmanagerpro24.data.source.StudentLocalDataSource
import com.ahona.studentmanagerpro24.domain.repository.StudentRepository

class StudentRepositoryImpl(
    private val localDataSource: StudentLocalDataSource
) : StudentRepository {

    override fun getStudents(): List<Student> {
        return localDataSource.getStudents()
    }

    override fun addStudent(student: Student) {
        localDataSource.addStudent(student)
    }

    override fun updateStudent(student: Student) {
        localDataSource.updateStudent(student)
    }

//    override fun deleteStudent(studentId: Int) {
//        localDataSource.deleteStudent(studentId)
//    }

    override fun deleteStudent(studentId: Int): Result<Unit> {
        return localDataSource.deleteStudent(studentId)
    }
}