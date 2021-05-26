package com.example.technpractiseandroid.tasks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.base.BaseFragment
import com.example.technpractiseandroid.databinding.TasksFragmentBinding
import com.example.technpractiseandroid.helpers.SwipeToDeleteCallback
import javax.inject.Inject

class AllTasksFragment : BaseFragment<AllTasksVM>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var allTasksVM: AllTasksVM
    lateinit var binding: TasksFragmentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        MyMainApplication.appComponent.inject(this)
        allTasksVM = ViewModelProvider(this, viewModelFactory).get(AllTasksVM::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TasksFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            vm = allTasksVM
            lifecycleOwner = this@AllTasksFragment
            rvTasksList.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = allTasksVM.taskAdapter
                addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            }
            progressBar.visibility = View.VISIBLE
        }

        val tag = arguments?.getString("tag")

        allTasksVM.loadTasks()

        allTasksVM.tasksList.observe(viewLifecycleOwner) { list ->
            if (tag != null) {
                Log.d("find bug", "all task fragment with tag ${tag.toString()}")
                allTasksVM.newListForSortByTag.observe(viewLifecycleOwner) { list2 ->
                    allTasksVM.taskAdapter.updateDataSource(list2)
                }
                allTasksVM.loadTasksByImportance(tag.toString())
                if (!allTasksVM.newListForSortByTag.value.isNullOrEmpty()) {
                    allTasksVM.taskAdapter.updateDataSource(allTasksVM.newListForSortByTag.value!!)
                }
                return@observe
            } else {
                allTasksVM.taskAdapter.updateDataSource(list)
            }
            binding.progressBar.visibility = View.GONE
        }

        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = false
            allTasksVM.tasksList.observe(viewLifecycleOwner) {
                allTasksVM.taskAdapter.updateDataSource(it)
            }
        }

        binding.swipe2.setOnRefreshListener {
            binding.swipe2.isRefreshing = false
        }
        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView() {
        val itemTouchHelper = ItemTouchHelper(object : SwipeToDeleteCallback(binding.rvTasksList) {
            override fun instantiateUnderlayButton(position: Int): List<UnderlayButton> {
                var buttons = listOf<UnderlayButton>()
                val deleteButton = deleteButton(position)
                buttons = listOf(deleteButton)
                return buttons
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.rvTasksList)
    }

    private fun deleteButton(position: Int): SwipeToDeleteCallback.UnderlayButton {
        return SwipeToDeleteCallback.UnderlayButton(
            requireContext(),
            "Delete",
            14.0f,
            android.R.color.holo_red_light,
            object : SwipeToDeleteCallback.UnderlayButtonClickListener {
                override fun onClick() {
                    allTasksVM.apply {
                        deleteTask(position)
                        taskAdapter.removeItem(position)
                        tasksList.observe(viewLifecycleOwner) { list ->
                            allTasksVM.taskAdapter.updateDataSource(list)
                        }
                    }
                }
            })
    }
}