package com.ahona.studentmanagerpro24.data.source

import com.ahona.studentmanagerpro24.data.model.Student

class StudentLocalDataSource {

    private val students = mutableListOf(
        Student(
            id = 1,
            name = "Rahul",
            age = 20,
            course = "Android Development"
        ),
        Student(
            id = 2,
            name = "Priya",
            age = 22,
            course = "Kotlin"
        )
    )

    fun getStudents(): List<Student> {
        return students.toList()
    }

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun updateStudent(updatedStudent: Student) {
        val index = students.indexOfFirst {
            it.id == updatedStudent.id
        }

        if (index != -1) {
            students[index] = updatedStudent
        }
    }

//    fun deleteStudent(studentId: Int) {
//        students.removeAll {
//            it.id == studentId
//        }
//    }

    fun deleteStudent(studentId: Int): Result<Unit> {

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