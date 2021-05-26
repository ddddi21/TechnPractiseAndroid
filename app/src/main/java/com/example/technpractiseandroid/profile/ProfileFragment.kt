package com.example.technpractiseandroid.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.R
import com.example.technpractiseandroid.base.*
import com.example.technpractiseandroid.databinding.ProfileFragmentBinding
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
            toFun()
        }
        return binding.root

    }

    private fun toFun(){
        navigationController.navigate(R.id.action_profileFragment_to_getFunFragment)
    }
}