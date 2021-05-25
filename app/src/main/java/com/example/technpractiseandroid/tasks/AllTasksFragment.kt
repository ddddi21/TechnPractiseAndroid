package com.example.technpractiseandroid.tasks

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.auth.login.LoginVM
import com.example.technpractiseandroid.base.BaseFragment
import com.example.technpractiseandroid.databinding.SignInFragmentBinding
import com.example.technpractiseandroid.databinding.TasksFragmentBinding
import javax.inject.Inject

class AllTasksFragment: BaseFragment<AllTasksVM> (){
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
        return binding.root
    }
}