package co.lap.todolap.viewmodel

import androidx.lifecycle.viewModelScope
import co.lap.todolap.base.BaseViewModel
import co.lap.todolap.data.task.Task
import co.lap.todolap.data.task.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(private val taskRepository: TaskRepository) : BaseViewModel() {
    private val TAG = "TaskViewModel"

    val tasks = taskRepository.getAllTasks()

    fun getTask(id : Int) = taskRepository.getTask(id)

    fun deleteAllTasks() = viewModelScope.launch {
        taskRepository.deleteAllTasks()
    }

    fun addTask(task : Task) = viewModelScope.launch {
        taskRepository.insertTask(task)
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        taskRepository.deleteTask(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        taskRepository.updateTask(task)
    }
}