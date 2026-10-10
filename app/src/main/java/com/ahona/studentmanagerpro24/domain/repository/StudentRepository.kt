package com.ahona.studentmanagerpro24.domain.repository

import com.ahona.studentmanagerpro24.data.model.Student
import kotlinx.coroutines.flow.Flow

interface StudentRepository {

//    fun getStudents(): List<Student>
  fun getStudents(): Flow<List<Student>>

    suspend fun addStudent(student: Student)

    suspend fun updateStudent(student: Student)

    suspend fun deleteStudent(studentId: Int): Result<Unit>
}