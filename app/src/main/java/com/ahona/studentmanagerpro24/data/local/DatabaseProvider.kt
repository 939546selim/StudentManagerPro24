package com.ahona.studentmanagerpro24.data.local

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

//    simple varsion

//    fun getDatabase(context: Context): AppDatabase {
//        return Room.databaseBuilder(
//            context,
//            AppDatabase::class.java,
//            "student_database"
//        ).build()
//    }




    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {

        return INSTANCE ?: synchronized(this) {

            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "student_database"
            ).build().also {
                INSTANCE = it
            }
        }
    }

}