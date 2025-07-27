package com.example.mytask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mytask.data.TaskDatabase
import com.example.mytask.repository.TaskRepository
import com.example.mytask.ui.theme.MyTaskTheme
import com.example.mytask.viewmodel.TaskViewModel
import com.example.mytask.viewmodel.TaskViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = TaskDatabase.getDatabase(this)
        val repository = TaskRepository(db.taskDao())
        val factory = TaskViewModelFactory(repository)

        setContent {
            MyTaskTheme {
                val viewModel: TaskViewModel = viewModel(factory = factory)
                NavGraph(viewModel)
            }
        }
    }
}
