package com.example.technpractiseandroid.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.R
import com.example.technpractiseandroid.base.BaseFragment
import com.example.technpractiseandroid.base.navigationController
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
        val bundle = Bundle()
        binding.tvHomePageTagFast.setOnClickListener {
            Log.d("find bug","HomeScreenFragment with tag fast}")
            bundle.putString("tag", "fast")
            navigationController.navigate(R.id.action_homePageFragment_to_allTasksFragment,
                bundle)
        }
        binding.tvHomePageTagMedium.setOnClickListener {
            bundle.putString("tag", "medium")
            navigationController.navigate(R.id.action_homePageFragment_to_allTasksFragment,
               bundle)
        }
        binding.tvHomePageTagLight.setOnClickListener {
            bundle.putString("tag", "can wait")
            navigationController.navigate(R.id.action_homePageFragment_to_allTasksFragment,
                bundle)
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeVM.setUsername()
    }


}