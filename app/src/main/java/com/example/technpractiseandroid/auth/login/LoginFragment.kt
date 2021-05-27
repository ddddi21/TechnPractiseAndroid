package com.example.technpractiseandroid.auth.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.R
import com.example.technpractiseandroid.base.BaseFragment
import com.example.technpractiseandroid.base.navigationController
import com.example.technpractiseandroid.base.startApp
import com.example.technpractiseandroid.databinding.SignInFragmentBinding
import kotlinx.coroutines.*
import javax.inject.Inject

class LoginFragment : BaseFragment<LoginVM>() {

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
    ): View {
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
                        binding.progressBar.visibility = View.VISIBLE
                        loginVM.userInteractor.login(
                            loginVM.email.value.toString(),
                            loginVM.password.value.toString(),
                            loginVM.loginErrorMessage
                        )
                        Log.d("find bug", "loginErrorMessage ${loginVM.loginErrorMessage}")
                        if (!loginVM.loginErrorMessage.value.isNullOrEmpty()) {
                            Toast.makeText(
                                context,
                                loginVM.loginErrorMessage.value,
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        } else {
                            binding.progressBar.visibility = View.GONE
                            activity?.startApp()
                        }
                    } catch (throwable: Throwable) {
                        Toast.makeText(context, throwable.message.toString(), Toast.LENGTH_SHORT)
                            .show()
                        Log.d("find bug", throwable.message.toString())
                    } finally {
                        binding.progressBar.visibility = View.GONE
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

    private fun onRegistration() {
        navigationController.navigate(R.id.action_loginFragment_to_registrationFragment)
    }
}