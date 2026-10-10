package com.ahona.studentmanagerpro24.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahona.studentmanagerpro24.data.model.Student

@Database(
    entities = [Student::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun studentDao(): StudentDao
}