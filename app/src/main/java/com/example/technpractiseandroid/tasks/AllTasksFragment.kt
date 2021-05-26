package com.example.technpractiseandroid.tasks

import android.opengl.Visibility
import android.os.Bundle
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
import com.example.technpractiseandroid.auth.login.LoginVM
import com.example.technpractiseandroid.base.BaseFragment
import com.example.technpractiseandroid.databinding.SignInFragmentBinding
import com.example.technpractiseandroid.databinding.TasksFragmentBinding
import com.example.technpractiseandroid.helpers.SwipeToDeleteCallback
import javax.inject.Inject

class AllTasksFragment: BaseFragment<AllTasksVM> (){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var allTasksVM: AllTasksVM
    lateinit var binding: TasksFragmentBinding

    private var toast: Toast? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        MyMainApplication.appComponent.inject(this)
        allTasksVM = ViewModelProvider(this, viewModelFactory).get(AllTasksVM::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            //TODO(fix)
        }
        allTasksVM.tasksList.observe(viewLifecycleOwner, {list ->
            allTasksVM.taskAdapter.updateDataSource(list)
            binding.progressBar.visibility = View.GONE
        })
        allTasksVM.loadTasks()

        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = false
        }
        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView() {
        val itemTouchHelper = ItemTouchHelper(object : SwipeToDeleteCallback(binding.rvTasksList) {
            override fun instantiateUnderlayButton(position: Int): List<UnderlayButton> {
                var buttons = listOf<UnderlayButton>()
                val deleteButton = deleteButton(position)
                val markAsUnreadButton = markAsUnreadButton(position)
                val archiveButton = archiveButton(position)
//                when (position) {
//                    1 -> buttons = listOf(deleteButton)
//                    2 -> buttons = listOf(deleteButton, markAsUnreadButton)
//                    3 -> buttons = listOf(deleteButton, markAsUnreadButton, archiveButton)
//                    else -> Unit
//                }
                buttons = listOf(deleteButton)
                return buttons
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.rvTasksList)
    }

    private fun toast(text: String) {
        toast?.cancel()
        toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        toast?.show()
    }

    private fun deleteButton(position: Int) : SwipeToDeleteCallback.UnderlayButton {
        return SwipeToDeleteCallback.UnderlayButton(
            requireContext(),
            "Delete",
            14.0f,
            android.R.color.holo_red_light,
            object : SwipeToDeleteCallback.UnderlayButtonClickListener {
                override fun onClick() {
                    toast("Deleted item $position")
                }
            })
    }

    private fun markAsUnreadButton(position: Int) : SwipeToDeleteCallback.UnderlayButton {
        return SwipeToDeleteCallback.UnderlayButton(
            requireContext(),
            "Mark as unread",
            14.0f,
            android.R.color.holo_green_light,
            object : SwipeToDeleteCallback.UnderlayButtonClickListener {
                override fun onClick() {
                    toast("Marked as unread item $position")
                }
            })
    }

    private fun archiveButton(position: Int) : SwipeToDeleteCallback.UnderlayButton {
        return SwipeToDeleteCallback.UnderlayButton(
            requireContext(),
            "Archive",
            14.0f,
            android.R.color.holo_blue_light,
            object : SwipeToDeleteCallback.UnderlayButtonClickListener {
                override fun onClick() {
                    toast("Archived item $position")
                }
            })
    }
}