package co.lap.todolap.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.lap.todolap.R
import co.lap.todolap.base.BaseFragment
import co.lap.todolap.common.DialogFabric
import co.lap.todolap.data.task.Task
import co.lap.todolap.presentation.adapters.TaskListAdapter
import co.lap.todolap.presentation.adapters.TaskListListener
import co.lap.todolap.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_todo.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class TodoFragment : BaseFragment(), TaskListListener {
    private val TAG = "HomeFragment"
    private val taskViewModel by viewModel<TaskViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab_todo_fragment.setOnClickListener{ view -> view.findNavController().navigate(R.id.action_navTodoFragment_to_addTaskDialog) }

        val calendar = Calendar.getInstance()
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val year = calendar.get(Calendar.YEAR)
        val monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
        val dayLongName = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())

        tvTodayDate.text = "${dayOfMonth} ${monthName} ${year}, ${dayLongName}"

        ivCalendarIcon.setOnClickListener {
            showMaterialDialog(DialogFabric.TYPE_SUCCESS, "Button clicked")
        }

        val recyclerView = requireActivity().findViewById<RecyclerView>(R.id.rvTasks)
        val adapter =
            TaskListAdapter(this, requireContext())

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        taskViewModel.tasks.observe(viewLifecycleOwner, Observer {
            adapter.setTasks(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onTaskClicked(task: Task, position: Int) {
        val bundle = bundleOf(
            Pair("Task", task)
        )
        findNavController().navigate(R.id.taskDialog, bundle)
    }

    override fun onTaskChecked(task: Task, position: Int, value: Boolean) {
        task.completed = value
        taskViewModel.updateTask(task)
    }
}

































