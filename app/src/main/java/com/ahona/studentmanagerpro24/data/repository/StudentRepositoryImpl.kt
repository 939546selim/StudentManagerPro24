package com.ahona.studentmanagerpro24.data.repository

import com.ahona.studentmanagerpro24.data.model.Student
import com.ahona.studentmanagerpro24.domain.repository.StudentRepository
import com.ahona.studentmanagerpro24.data.source.StudentRoomDataSource
import kotlinx.coroutines.flow.Flow

class StudentRepositoryImpl(
    private val localDataSource: StudentRoomDataSource
) : StudentRepository {

//    override fun getStudents(): List<Student> {
//        return localDataSource.getStudents()
//    }

    override fun getStudents(): Flow<List<Student>> {
        return localDataSource.getStudents()
    }

//    override fun addStudent(student: Student) {
//        localDataSource.addStudent(student)
//    }

    override suspend fun addStudent(student: Student) {
        localDataSource.addStudent(student)
    }

//    override fun updateStudent(student: Student) {
//        localDataSource.updateStudent(student)
//    }

    override suspend fun updateStudent(student: Student) {
        localDataSource.updateStudent(student)
    }

//    override fun deleteStudent(studentId: Int) {
//        localDataSource.deleteStudent(studentId)
//    }

//    override fun deleteStudent(studentId: Int): Result<Unit> {
//        return localDataSource.deleteStudent(studentId)
//    }
override suspend fun deleteStudent(
    studentId: Int
): Result<Unit> {
    return localDataSource.deleteStudent(studentId)
}
}