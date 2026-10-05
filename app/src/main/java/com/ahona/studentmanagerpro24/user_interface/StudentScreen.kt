package com.ahona.studentmanagerpro24.user_interface


//
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Button
//import androidx.compose.material3.Card
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.ahona.studentmanagerpro24.data.model.Student
//import com.ahona.studentmanagerpro24.viewmodel.StudentViewModel
//
//@Composable
//fun StudentScreen(
//    viewModel: StudentViewModel
//) {
//    val students by viewModel.students.collectAsState()
//
//    var name by remember { mutableStateOf("") }
//    var age by remember { mutableStateOf("") }
//    var course by remember { mutableStateOf("") }
//    var errorMessage by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(10.dp)
//    ) {
//        Text(
//            text = "Student Manager Pro 24",
//            style = MaterialTheme.typography.headlineSmall
//        )
//
//        OutlinedTextField(
//            value = name,
//            onValueChange = { name = it },
//            label = { Text("Student Name") },
//            modifier = Modifier.fillMaxWidth(),
//            singleLine = true
//        )
//
//        OutlinedTextField(
//            value = age,
//            onValueChange = { age = it },
//            label = { Text("Age") },
//            modifier = Modifier.fillMaxWidth(),
//            singleLine = true
//        )
//
//        OutlinedTextField(
//            value = course,
//            onValueChange = { course = it },
//            label = { Text("Course") },
//            modifier = Modifier.fillMaxWidth(),
//            singleLine = true
//        )
//
//        Button(
//            onClick = {
//                val studentName = name.trim()
//                val studentAge = age.toIntOrNull()
//                val studentCourse = course.trim()
//
//                errorMessage = ""
//
//                if (studentName.isBlank()) {
//                    errorMessage = "Name is required"
//                } else if (studentAge == null || studentAge <= 0) {
//                    errorMessage = "Enter a valid age"
//                } else if (studentCourse.isBlank()) {
//                    errorMessage = "Course is required"
//                } else {
//                    val nextId =
//                        (students.maxOfOrNull { it.id } ?: 0) + 1
//
//                    val newStudent = Student(
//                        id = nextId,
//                        name = studentName,
//                        age = studentAge,
//                        course = studentCourse
//                    )
//
//                    viewModel.addStudent(newStudent)
//
//                    name = ""
//                    age = ""
//                    course = ""
//                }
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Add Student")
//        }
//
//        if (errorMessage.isNotEmpty()) {
//            Text(
//                text = errorMessage,
//                color = MaterialTheme.colorScheme.error
//            )
//        }
//
//        Text(
//            text = "Total Students: ${students.size}",
//            style = MaterialTheme.typography.titleMedium
//        )
//
//        LazyColumn(
//            modifier = Modifier.weight(1f),
//            verticalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            items(
//                items = students,
//                key = { student -> student.id }
//            ) { student ->
//                Card(
//                    modifier = Modifier.fillMaxWidth()
//                ) {
////                    Row(
////                        modifier = Modifier
////                            .fillMaxWidth()
////                            .padding(12.dp),
////                        horizontalArrangement =
////                            Arrangement.SpaceBetween
////                    ) {
////                        Column {
////                            Text(
////                                text = student.name,
////                                style = MaterialTheme.typography.titleMedium
////                            )
////                            Text("Age: ${student.age}")
////                            Text("Course: ${student.course}")
////                        }
////                    }
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(12.dp),
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Column {
//                            Text(
//                                text = student.name,
//                                style = MaterialTheme.typography.titleMedium
//                            )
//
//                            Text("Age: ${student.age}")
//                            Text("Course: ${student.course}")
//                        }
//
//                        Button(
//                            onClick = {
//                                viewModel.deleteStudent(student.id)
//                            }
//                        ) {
//                            Text("Delete")
//                        }
//                    }
//                }
//            }
//        }
//    }
//}




import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahona.studentmanagerpro24.data.model.Student
import com.ahona.studentmanagerpro24.viewmodel.StudentViewModel

@Composable
fun StudentScreen(
    viewModel: StudentViewModel
) {
    val students by viewModel.students.collectAsState()

    val ErrorMessageModal by
    viewModel.errorMessage.collectAsState()

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var course by remember { mutableStateOf("") }

    var errorMessage by remember { mutableStateOf("") }

    // null means Add mode; a student ID means Edit mode.
    var editingStudentId by remember {
        mutableStateOf<Int?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Student Manager Pro 24",
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Student Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = course,
            onValueChange = { course = it },
            label = { Text("Course") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Button(
            onClick = {
                val studentName = name.trim()
                val studentAge = age.toIntOrNull()
                val studentCourse = course.trim()

                errorMessage = ""

                when {
                    studentName.isBlank() -> {
                        errorMessage = "Name is required"
                    }

                    studentAge == null || studentAge <= 0 -> {
                        errorMessage = "Enter a valid age"
                    }

                    studentCourse.isBlank() -> {
                        errorMessage = "Course is required"
                    }

                    else -> {
                        val student = Student(
                            id = editingStudentId
                                ?: ((students.maxOfOrNull {
                                    it.id
                                } ?: 0) + 1),
                            name = studentName,
                            age = studentAge,
                            course = studentCourse
                        )

                        if (editingStudentId == null) {
                            viewModel.addStudent(student)
                        } else {
                            viewModel.updateStudent(student)
                        }

                        name = ""
                        age = ""
                        course = ""
                        editingStudentId = null
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (editingStudentId == null) {
                    "Add Student"
                } else {
                    "Save Changes"
                }
            )
        }

        if (editingStudentId != null) {
            Button(
                onClick = {
                    name = ""
                    age = ""
                    course = ""
                    editingStudentId = null
                    errorMessage = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancel Edit")
            }
        }

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }

        Text(
            text = "Total Students: ${students.size}",
            style = MaterialTheme.typography.titleMedium
        )

        if (ErrorMessageModal != null) {
            Text(
                text = ErrorMessageModal!!,
                color = MaterialTheme.colorScheme.error
            )
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = students,
                key = { it.id }
            ) { student ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement =
                            Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = student.name,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text("Age: ${student.age}")
                        Text("Course: ${student.course}")

                        Row(
                            horizontalArrangement =
                                Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = {
                                    name = student.name
                                    age = student.age.toString()
                                    course = student.course
                                    editingStudentId = student.id
                                    errorMessage = ""
                                }
                            ) {
                                Text("Edit")
                            }

                            Button(
                                onClick = {
                                    viewModel.deleteStudent(student.id)

                                    if (editingStudentId == student.id) {
                                        name = ""
                                        age = ""
                                        course = ""
                                        editingStudentId = null
                                    }
                                }
                            ) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        }
    }
}

