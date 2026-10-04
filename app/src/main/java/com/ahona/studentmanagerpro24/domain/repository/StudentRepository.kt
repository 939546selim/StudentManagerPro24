package com.ahona.studentmanagerpro24.domain.repository

import com.ahona.studentmanagerpro24.data.model.Student

interface StudentRepository {

    fun getStudents(): List<Student>

    fun addStudent(student: Student)

    fun updateStudent(student: Student)

    fun deleteStudent(studentId: Int)
}