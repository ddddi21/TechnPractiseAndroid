package com.example.technpractiseandroid.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.base.BaseFragment
import com.example.technpractiseandroid.databinding.HomePageFragmentBinding
import javax.inject.Inject

class HomeScreenFragment : BaseFragment<HomeScreenVM>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var homeVM: HomeScreenVM

    lateinit var binding: HomePageFragmentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        MyMainApplication.appComponent.inject(this)
        homeVM = ViewModelProvider(this, viewModelFactory).get(HomeScreenVM::class.java)
        homeVM.randomSupport()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = HomePageFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.vm = homeVM
        homeVM.apply {
            loadTasksCount()
            loadTasksCountByImportanceImportant()
            loadTasksCountByImportanceMedium()
            loadTasksCountByImportanceLight()
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeVM.setUsername()
    }


}