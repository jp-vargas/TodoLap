package co.lap.todolap.presentation.adapters

import co.lap.todolap.data.task.Task

interface TaskListListener {
    fun onTaskClicked(task: Task, position: Int)
    fun onTaskChecked(task: Task, position: Int, value: Boolean)
}