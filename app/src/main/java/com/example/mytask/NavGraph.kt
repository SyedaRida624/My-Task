package com.example.mytask

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.mytask.uu.AddEditTaskScreen
import com.example.mytask.uu.TaskListScreen
import com.example.mytask.viewmodel.TaskViewModel


@Composable
fun NavGraph(viewModel: TaskViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "task_list") {
        composable("task_list") {
            TaskListScreen(viewModel, navController)
        }
        composable("add_edit_task?taskId={taskId}",
            arguments = listOf(navArgument("taskId") {
                type = NavType.IntType
                defaultValue = -1
            })) { backStackEntry ->
            AddEditTaskScreen(
                taskId = backStackEntry.arguments?.getInt("taskId") ?: -1,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}
