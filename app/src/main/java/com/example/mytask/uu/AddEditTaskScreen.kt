package com.example.mytask.uu

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytask.data.Task
import com.example.mytask.viewmodel.TaskViewModel

@Composable
fun AddEditTaskScreen(taskId: Int, viewModel: TaskViewModel, navController: NavController) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("") }

    LaunchedEffect(taskId) {
        if (taskId != -1) {
            viewModel.getTaskById(taskId) {
                it?.let {
                    title = it.title
                    description = it.description
                    date = it.date
                    priority = it.priority
                }
            }
        }
    }

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
        OutlinedTextField(value = date, onValueChange = { date = it }, label = { Text("Due Date") })
        OutlinedTextField(value = priority, onValueChange = { priority = it }, label = { Text("Priority") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val task = Task(id = if (taskId != -1) taskId else 0, title, description, date, priority)
            if (taskId != -1) viewModel.updateTask(task) else viewModel.addTask(task)
            navController.popBackStack()
        }) {
            Text(if (taskId != -1) "Update Task" else "Add Task")
        }
    }
}
