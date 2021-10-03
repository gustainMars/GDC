package com.gustainmars.gdcapplication.feature.data.repository

import androidx.lifecycle.LiveData
import com.gustainmars.gdcapplication.feature.data.entity.TaskDto
import com.gustainmars.gdcapplication.feature.data.local.TaskDao

class TaskRepository private constructor(
    private val localDataSource: TaskDao
) {

    suspend fun addTask(taskDto: TaskDto) {
        localDataSource.insert(taskDto)
    }

    fun getAllTasks(): LiveData<List<TaskDto>> = localDataSource.getAllTasks()

    companion object {

        fun create(localDataSource: TaskDao): TaskRepository {
            return TaskRepository(localDataSource)
        }
    }
}