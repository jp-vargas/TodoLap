package co.lap.todolap.data.task

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class TaskRepository (private val taskDao: TaskDao) {
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    fun getAllTasks() : LiveData<List<Task>> = taskDao.getAllTasks()

    fun getTask(id: Int) = taskDao.getTask(id)

    suspend fun deleteAllTasks() = taskDao.deleteAllTasks()

    suspend fun insertTask(task: Task) = taskDao.insertTask(task)

    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)

    suspend fun updateTask(task: Task) = taskDao.updateTask(task)
}
