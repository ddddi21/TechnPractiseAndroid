package com.example.technpractiseandroid.auth.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.R
import com.example.technpractiseandroid.base.BaseFragment
import com.example.technpractiseandroid.base.MainActivity
import com.example.technpractiseandroid.base.navigationController
import com.example.technpractiseandroid.base.startApp
import com.example.technpractiseandroid.databinding.SignInFragmentBinding
import com.example.technpractiseandroid.databinding.SignUpFragmentBinding
import com.example.technpractiseandroid.di.VMFactory
import com.example.technpractiseandroid.di.components.MainAppComponent
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.single
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class LoginFragment: BaseFragment<LoginVM>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var loginVM: LoginVM
    lateinit var binding: SignInFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        MyMainApplication.appComponent.inject(this)
        loginVM = ViewModelProvider(this, viewModelFactory).get(LoginVM::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SignInFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.vm = loginVM

        binding.tvSignInToSignUp.setOnClickListener {
            onRegistration()
        }

        binding.btnSignUpEnter.setOnClickListener {
                successLogin()
            val nevalid = loginVM.validForm()
            if (nevalid) {
                return@setOnClickListener
            } else {
                lifecycleScope.launch {
                    try {
                            loginVM.userInteractor.login(loginVM.email.value.toString(),
                                loginVM.password.value.toString(),
                                loginVM.loginErrorMessage)
                        if(!loginVM.loginErrorMessage.value.isNullOrEmpty()){
                            Toast.makeText(context, loginVM.loginErrorMessage.value, Toast.LENGTH_SHORT)
                                .show()
                        } else{
                            activity?.startApp()
                        }
                    } catch (throwable: Throwable){
                        Toast.makeText(context, throwable.message.toString(), Toast.LENGTH_SHORT)
                            .show()
                        Log.d("find bug", throwable.message.toString())
                    }
                }
            }
            }
        return binding.root
    }

    fun successLogin() {
            loginVM.onLoginClick()
            binding.tiSignUpUsername.error = loginVM.emailError.value
            binding.tiSignUpPassword.error = loginVM.passwordError.value
    }

    private fun onRegistration(){
        navigationController.navigate(R.id.action_loginFragment_to_registrationFragment)
    }
}