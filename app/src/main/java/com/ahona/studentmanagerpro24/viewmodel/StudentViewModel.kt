package com.ahona.studentmanagerpro24.viewmodel

import androidx.lifecycle.ViewModel
import com.ahona.studentmanagerpro24.data.model.Student
import com.ahona.studentmanagerpro24.domain.repository.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class StudentViewModel(
    private val repository: StudentRepository
) : ViewModel() {

//    private val _students =
//        MutableStateFlow(repository.getStudents())
//
//    val students: StateFlow<List<Student>> =
//        _students.asStateFlow()

    val students: StateFlow<List<Student>> =
        repository.getStudents().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    private val _errorMessage =
        MutableStateFlow<String?>(null)

    val errorMessage: StateFlow<String?> =
        _errorMessage.asStateFlow()

//    fun addStudent(student: Student) {
//        repository.addStudent(student)
//
//    }
    fun addStudent(student: Student) {
        viewModelScope.launch {
            repository.addStudent(student)
        }
    }

//    fun updateStudent(student: Student) {
//        repository.updateStudent(student)
//
//    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            repository.updateStudent(student)
        }
    }

//    fun deleteStudent(studentId: Int) {
//        repository.deleteStudent(studentId)
//        refreshStudents()
//    }

//    fun deleteStudent(studentId: Int): Result<Unit> {
//
//        val result = repository.deleteStudent(studentId)
//
//        if (result.isSuccess) {
//            refreshStudents()
//        }
//
//        return result
//    }

//    fun deleteStudent(studentId: Int): Result<Unit> {
//
//        val result = repository.deleteStudent(studentId)
//
//        if (result.isSuccess) {
//            _errorMessage.value = null
//            refreshStudents()
//        } else {
//            _errorMessage.value =
//                result.exceptionOrNull()?.message
//                    ?: "Unknown error"
//        }
//
//        return result
//    }

    fun deleteStudent(studentId: Int) {
        viewModelScope.launch {
            val result = repository.deleteStudent(studentId)

            if (result.isSuccess) {
                _errorMessage.value = null
            } else {
                _errorMessage.value =
                    result.exceptionOrNull()?.message ?: "Unknown error"
            }
        }
    }

//    private fun refreshStudents() {
//        _students.value = repository.getStudents()
//    }
}