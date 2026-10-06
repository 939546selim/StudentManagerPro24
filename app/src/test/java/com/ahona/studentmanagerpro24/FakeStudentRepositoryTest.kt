package com.ahona.studentmanagerpro24
import com.ahona.studentmanagerpro24.fake.FakeStudentRepository
import org.junit.Before
import org.junit.Test
import com.ahona.studentmanagerpro24.data.model.Student
import junit.framework.TestCase.assertEquals

class FakeStudentRepositoryTest {
    private lateinit var repository: FakeStudentRepository

    @Before
    fun setup() {
        repository = FakeStudentRepository()
    }

    @Test
    fun addStudent_addsStudentToRepository() {

        val student = Student(
            id = 1,
            name = "Test Student",
            age = 20,
            course = "Kotlin"
        )

        repository.addStudent(student)

        assertEquals(
            1,
            repository.getStudents().size
        )
    }

    @Test
    fun updateStudent_updatesStudentInRepository() {

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

        repository.updateStudent(updatedStudent)

        assertEquals(
            "Rahul Kumar",
            repository.getStudents().first().name
        )

    }

    @Test
    fun deleteStudent_removesStudentFromRepository() {

        val student = Student(
            id = 1,
            name = "Rahul",
            age = 20,
            course = "Kotlin"
        )

        repository.addStudent(student)

        repository.deleteStudent(student.id)

        assertEquals(
            0,
            repository.getStudents().size
        )

    }

    @Test
    fun deleteStudent_whenStudentNotFound_returnsFailure() {

        val result = repository.deleteStudent(999)

        assertEquals(true, result.isFailure)

    }
}