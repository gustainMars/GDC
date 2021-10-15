package com.gustainmars.gdcapplication.feature.presentation

import android.app.Application
import androidx.lifecycle.*
import com.gustainmars.gdcapplication.base.AppDataBase
import com.gustainmars.gdcapplication.feature.data.entity.TaskDto
import com.gustainmars.gdcapplication.feature.data.local.TaskDao
import com.gustainmars.gdcapplication.feature.data.repository.TaskRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class TaskListViewModel (application: Application): AndroidViewModel(application) {

    private val repository: TaskRepository
    val alltasks: LiveData<List<TaskDto>>

    init {
        val dao: TaskDao = AppDataBase.getDataBase(application).taskDao()
        repository = TaskRepository.create(dao)
        alltasks = repository.getAllTasks()

    }

    fun addTask(taskDto: TaskDto) {
        viewModelScope.launch {
            repository.addTask(taskDto)
        }
    }

    class TaskViewModelFactory constructor(private val application: Application) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(TaskListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                TaskListViewModel(this.application) as T
            } else {
                throw IllegalArgumentException("ViewModel not found")
            }
        }
    }
}