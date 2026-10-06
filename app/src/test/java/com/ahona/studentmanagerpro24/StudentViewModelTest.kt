package com.ahona.studentmanagerpro24


import com.ahona.studentmanagerpro24.fake.FakeStudentRepository
import com.ahona.studentmanagerpro24.viewmodel.StudentViewModel
import com.ahona.studentmanagerpro24.data.model.Student
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class StudentViewModelTest {

    private lateinit var repository: FakeStudentRepository
    private lateinit var viewModel: StudentViewModel

    @Before
    fun setup() {
        repository = FakeStudentRepository()
        viewModel = StudentViewModel(repository)
    }

    @Test
    fun addStudent_addsStudentToList() {

        val student = Student(
            id = 1,
            name = "Test Student",
            age = 20,
            course = "Kotlin"
        )

        viewModel.addStudent(student)

        assertEquals(1, viewModel.students.value.size)

    }

    @Test
    fun updateStudent_updatesStudent() {

        val originalStudent = Student(
            id = 1,
            name = "Rahul",
            age = 20,
            course = "Kotlin"
        )

        repository.addStudent(originalStudent)

        val updatedStudent = Student(
            id = 1,
            name = "Rahul Kumar",
            age = 21,
            course = "Jetpack Compose"
        )

        viewModel.updateStudent(updatedStudent)

        assertEquals(
            "Rahul Kumar",
            viewModel.students.value.first().name
        )
    }

    @Test
    fun deleteStudent_removesStudentFromList() {

        val student = Student(
            id = 1,
            name = "Rahul",
            age = 20,
            course = "Kotlin"
        )

        repository.addStudent(student)

        viewModel.deleteStudent(student.id)

        assertEquals(
            0,
            viewModel.students.value.size
        )

    }

    @Test
    fun deleteStudent_whenStudentNotFound_returnsFailure() {

        val result = viewModel.deleteStudent(999)

        assertEquals(true, result.isFailure)

    }



}
