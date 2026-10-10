package com.ahona.studentmanagerpro24.data.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey
    val id: Int,
    val name: String,
    val age: Int,
    val course: String
)