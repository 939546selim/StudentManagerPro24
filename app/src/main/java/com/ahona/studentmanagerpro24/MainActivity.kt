package com.ahona.studentmanagerpro24

//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import com.ahona.studentmanagerpro24.ui.theme.StudentManagerPro24Theme
//import com.ahona.studentmanagerpro24.user_interface.StudentScreen
//import com.ahona.studentmanagerpro24.viewmodel.StudentViewModel
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
////            StudentManagerPro24Theme {
////                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
////                    Greeting(
////                        name = "Android",
////                        modifier = Modifier.padding(innerPadding)
////                    )
////                }
////            }
//            MaterialTheme {
//                StudentScreen(
//                    viewModel = studentViewModel
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    StudentManagerPro24Theme {
//        Greeting("Android")
//    }
//}

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.ahona.studentmanagerpro24.data.local.DatabaseProvider
import com.ahona.studentmanagerpro24.data.repository.StudentRepositoryImpl
import com.ahona.studentmanagerpro24.data.source.StudentLocalDataSource
import com.ahona.studentmanagerpro24.domain.repository.StudentRepository
import com.ahona.studentmanagerpro24.user_interface.StudentScreen
import com.ahona.studentmanagerpro24.viewmodel.StudentViewModel
import com.ahona.studentmanagerpro24.viewmodel.StudentViewModelFactory
import com.ahona.studentmanagerpro24.data.source.StudentRoomDataSource

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val localDataSource = StudentLocalDataSource()
        val database = DatabaseProvider.getDatabase(this)

        val roomDataSource = StudentRoomDataSource(
            database.studentDao()

        )

        val repository: StudentRepository =
            StudentRepositoryImpl(roomDataSource)

//        val repository: StudentRepository =
//            StudentRepositoryImpl(localDataSource)

        val factory = StudentViewModelFactory(repository)

        val studentViewModel =
            ViewModelProvider(this, factory)
                .get(StudentViewModel::class.java)

        setContent {
            MaterialTheme {
                val students by
                studentViewModel.students.collectAsState()


                StudentScreen(
                    viewModel = studentViewModel
                )
            }
        }
    }
}