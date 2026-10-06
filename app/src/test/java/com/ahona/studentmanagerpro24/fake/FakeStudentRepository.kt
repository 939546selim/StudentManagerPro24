package com.ahona.studentmanagerpro24.fake
import com.ahona.studentmanagerpro24.data.model.Student

import com.ahona.studentmanagerpro24.domain.repository.StudentRepository

class FakeStudentRepository : StudentRepository {

    private val students = mutableListOf<Student>()

    override fun getStudents(): List<Student> {
        return students.toList()
    }

    override fun addStudent(student: Student) {
        students.add(student)
    }

    override fun updateStudent(student: Student) {
        val index = students.indexOfFirst {
            it.id == student.id
        }

        if (index != -1) {
            students[index] = student
        }
    }

    override fun deleteStudent(studentId: Int): Result<Unit> {
        val removed = students.removeIf {
            it.id == studentId
        }

        return if (removed) {
            Result.success(Unit)
        } else {
            Result.failure(
                Exception("Student not found")
            )
        }
    }

}