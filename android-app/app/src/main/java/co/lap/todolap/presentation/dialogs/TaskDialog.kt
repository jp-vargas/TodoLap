package co.lap.todolap.presentation.dialogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import co.lap.todolap.R
import co.lap.todolap.data.task.Task
import co.lap.todolap.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.dialog_add_task.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskDialog : DialogFragment() {
    private val TAG : String = "AddTaskDialog"
    private lateinit var mTask : Task
    private val taskViewModel by viewModel<TaskViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_add_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    private fun init() {
        val task = arguments?.getSerializable("Task") as? Task

        if (task !== null) {
            mTask = task
            edtTaskTitle.editText?.setText(task.taskTitle)
            edtTaskDescription.editText?.setText(task.taskDescription)
        }

        // Toolbar
        toolbar_add_task.setNavigationOnClickListener { dismiss() }
        toolbar_add_task.setOnMenuItemClickListener{ menuItem ->
            when (menuItem.itemId) {
                R.id.delete -> {
                    Log.e(TAG, "Delete pressed")
                    onDeleteTask()
                    true
                }
                R.id.add -> {
                    Log.e(TAG, "Add pressed")
                    onNewTask(this::mTask.isInitialized)
                    true
                }
                else -> false
            }
        }

        // Spinners

        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.category_spinner,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spCategory.adapter = adapter
            }
        }

        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.priority_spinner,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spPriority.adapter = adapter
            }
        }
    }

    private fun onDeleteTask() {
        if (this::mTask.isInitialized) {
            taskViewModel.deleteTask(mTask)
            dismiss()
        }
    }


    private fun onNewTask(shouldUpdate: Boolean) {
        val taskTitle = edtTaskTitle.editText?.text.toString()
        val taskDescription = edtTaskDescription.editText?.text.toString()
        val taskCategory = spCategory.selectedItem.toString()
        val taskPriority = spPriority.selectedItem.toString()
        var error = false

        if (taskTitle.length <= 3) {
            edtTaskTitle.error = getString(R.string.error_text_field)
            edtTaskTitle.isErrorEnabled = true
            error = true
        }

        if (taskDescription.length <= 5) {
            edtTaskDescription.error = getString(R.string.error_text_field)
            edtTaskDescription.isErrorEnabled = true
            error = true
        }

        if (!error && !shouldUpdate) {
            mTask = Task(
                taskTitle = taskTitle,
                taskDescription = taskDescription,
                category = taskCategory,
                priority = taskPriority
            )

            taskViewModel.addTask(mTask)
            dismiss()
        }

        if (!error && shouldUpdate) {
            mTask.taskTitle = taskTitle
            mTask.taskDescription = taskDescription
            mTask.category = taskCategory
            mTask.priority = taskPriority

            taskViewModel.updateTask(mTask)
            dismiss()
        }
    }
}