package co.lap.todolap.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.lap.todolap.R
import co.lap.todolap.data.task.Task
import java.text.SimpleDateFormat
import java.util.*

class TaskListAdapter(private val taskListListener: TaskListListener, context: Context) : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {
    private val inflater : LayoutInflater =  LayoutInflater.from(context)
    private var tasks = emptyList<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val taskView = inflater.inflate(R.layout.item_main_task, parent, false)
        return ViewHolder(taskView)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dateFormat = "dd/MM/yyyy hh:mm"
        val formatter = SimpleDateFormat(dateFormat)
        val task = tasks[position]
        holder.mainTaskTitle.text = task.taskTitle
        holder.tvItemMainTaskDescription.text = task.taskDescription
        holder.tvItemMainTaskDuration.text = formatter.format(task.dateEnd?.let { Date(it) })
        holder.cbItemMainTask.isChecked = task.completed!!

        holder.cbItemMainTask.setOnClickListener { taskListListener.onTaskChecked(task, position, holder.cbItemMainTask.isChecked)}
        holder.itemView.setOnClickListener { taskListListener.onTaskClicked(task, position) }
    }

    fun setTasks(tasks : List<Task>) {
        this.tasks = tasks
    }

    inner class ViewHolder(taskView : View) : RecyclerView.ViewHolder(taskView) {
        val mainTaskTitle: TextView = itemView.findViewById(R.id.tvItemMainTaskTitle)
        val tvItemMainTaskDuration: TextView = itemView.findViewById(R.id.tvItemMainTaskDuration)
        val tvItemMainTaskDescription: TextView = itemView.findViewById(R.id.tvItemMainTaskDescription)
        val cbItemMainTask : CheckBox = itemView.findViewById(R.id.cbItemMainTask)
    }
}