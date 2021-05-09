package com.example.technpractiseandroid.auth.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.R
import com.example.technpractiseandroid.base.BaseFragment
import com.example.technpractiseandroid.base.navigationController
import com.example.technpractiseandroid.databinding.SignInFragmentBinding
import com.example.technpractiseandroid.databinding.SignUpFragmentBinding
import com.example.technpractiseandroid.di.VMFactory
import com.example.technpractiseandroid.di.components.MainAppComponent
import javax.inject.Inject

class LoginFragment: BaseFragment<LoginVM>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var loginVM: LoginVM =
        ViewModelProvider(this, viewModelFactory).get(LoginVM::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        MyMainApplication().authComponent().inject(fragment = this@LoginFragment)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = SignInFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.vm = loginVM

        val enterBtn = (view as ViewGroup).findViewById<Button>(R.id.btn_sign_up_enter)
        val toRegistration = (view as ViewGroup).findViewById<TextView>(R.id.tv_sign_in_to_sign_up)

        toRegistration.setOnClickListener {
            onRegistration()
        }

//        enterBtn.setOnClickListener {
//            errorMessages(view as ViewGroup)
//            if(!isHaveError){
//                viewModel.onRegistrationClick()
//            }
//        }
        return binding.root
    }

    private fun onRegistration(){
        navigationController.navigate(R.id.action_loginFragment_to_registrationFragment)
    }
}