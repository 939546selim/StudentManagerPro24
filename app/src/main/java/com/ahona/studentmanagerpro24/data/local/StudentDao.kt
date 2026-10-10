package com.ahona.studentmanagerpro24.data.local

import androidx.room.Dao
import androidx.room.Insert
import com.ahona.studentmanagerpro24.data.model.Student
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
//    @Insert
//    fun insertStudent(student: Student)
//
//
    @Query("SELECT * FROM students")
    fun getStudents(): Flow<List<Student>>
//
//    @Update
//    fun updateStudent(student: Student)
//
//    @Delete
//    fun deleteStudent(student: Student)

    @Insert
    suspend fun insertStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("SELECT * FROM students WHERE id = :studentId LIMIT 1")
    suspend fun findStudentById(studentId: Int): Student?

}