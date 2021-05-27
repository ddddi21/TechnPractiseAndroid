package com.example.technpractiseandroid.profile.`fun`

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.lifecycle.ViewModelProvider
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.R
import com.example.technpractiseandroid.base.BaseFragment
import com.example.technpractiseandroid.base.navigationController
import com.example.technpractiseandroid.databinding.GetFunFragmentBinding
import javax.inject.Inject

class GetFunFragment : BaseFragment<GetFunVM>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var getFunVM: GetFunVM

    override fun onCreate(savedInstanceState: Bundle?) {
        MyMainApplication.appComponent.inject(this)
        getFunVM = ViewModelProvider(this, viewModelFactory).get(GetFunVM::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = GetFunFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.vm = getFunVM
        getFunVM.isHasLink.observe(viewLifecycleOwner) {
            if (getFunVM.isHasLink.value == false) {
                binding.tvActivityLink.visibility = View.GONE
            } else {
                binding.tvActivityLink.visibility = View.VISIBLE
            }
        }
        getFunVM.letsFun()
        binding.btnAgain.setOnClickListener {
            getFunVM.letsFun()
        }

        val bundle = Bundle()
        binding.tvAdd.setOnClickListener {
            bundle.putString("actionName", getFunVM.activity.value)
            bundle.putString("actionDesc", getFunVM.type.value)
            navigationController.navigate(
                R.id.action_getFunFragment_to_createTaskFragment,
                bundle)
        }

        return binding.root
    }
}