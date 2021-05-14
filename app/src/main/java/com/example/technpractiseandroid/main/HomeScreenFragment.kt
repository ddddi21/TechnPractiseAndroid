package com.example.technpractiseandroid.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.R
import com.example.technpractiseandroid.auth.login.LoginVM
import com.example.technpractiseandroid.base.BaseFragment
import com.example.technpractiseandroid.base.navigationController
import com.example.technpractiseandroid.databinding.HomePageFragmentBinding
import com.example.technpractiseandroid.databinding.SignInFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class HomeScreenFragment: BaseFragment<HomeScreenVM>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var vm: HomeScreenVM

    lateinit var binding: HomePageFragmentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        MyMainApplication.appComponent.inject(this)
        vm = ViewModelProvider(this, viewModelFactory).get(HomeScreenVM::class.java)
//        vm.mAuth.addAuthStateListener(vm.firebaseAuthListener)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = HomePageFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.vm = vm
        return binding.root

    }
    //TODO(fix user setting delay)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.setUsername()
    }


}