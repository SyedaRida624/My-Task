package com.example.mytask.repository

import com.example.mytask.data.Task
import com.example.mytask.data.TaskDao

class TaskRepository(private val dao: TaskDao) {
    val tasks = dao.getAllTasks()

    suspend fun insert(task: Task) = dao.insert(task)
    suspend fun update(task: Task) = dao.update(task)
    suspend fun delete(task: Task) = dao.delete(task)
    suspend fun getTaskById(id: Int) = dao.getTaskById(id)
}
