package com.example.technpractiseandroid.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.auth.login.LoginVM
import com.example.technpractiseandroid.base.BaseFragment
import com.example.technpractiseandroid.base.MainActivity
import com.example.technpractiseandroid.base.startApp
import com.example.technpractiseandroid.base.startLoginActivity
import com.example.technpractiseandroid.databinding.ProfileFragmentBinding
import com.example.technpractiseandroid.databinding.SignInFragmentBinding
import javax.inject.Inject

class ProfileFragment: BaseFragment<ProfileVm>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var profileVM: ProfileVm

    override fun onCreate(savedInstanceState: Bundle?) {
        MyMainApplication.appComponent.inject(this)
        profileVM = ViewModelProvider(this, viewModelFactory).get(ProfileVm::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ProfileFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.vm = profileVM
        binding.logOut.setOnClickListener {
            profileVM.logOut()
            activity?.startLoginActivity()
        }
        binding.tvFun.setOnClickListener {
            profileVM.letsFun()
        }
        return binding.root

    }
}