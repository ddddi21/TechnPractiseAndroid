package com.example.technpractiseandroid.auth.registration

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.R
import com.example.technpractiseandroid.base.BaseFragment
import com.example.technpractiseandroid.base.navigationController
import com.example.technpractiseandroid.base.startApp
import com.example.technpractiseandroid.databinding.SignUpFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationFragment : BaseFragment<RegistrationVM>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mAuth: FirebaseAuth

    lateinit var registrationVM: RegistrationVM

    lateinit var binding: SignUpFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        MyMainApplication.appComponent.inject(fragment = this@RegistrationFragment)
        registrationVM = ViewModelProvider(this, viewModelFactory).get(RegistrationVM::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignUpFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.vm = registrationVM

        binding.tvSignUpToSignIn.setOnClickListener {
            onLogin()
        }

        binding.btnSignUpEnter.setOnClickListener {
            createAccount()
            val nevalid = registrationVM.validForm()
            if (nevalid) {
                return@setOnClickListener
            } else {
                lifecycleScope.launch {
                    try {
                        registrationVM.userInteractor.createUser(
                            registrationVM.email.value.toString(),
                            registrationVM.password.value.toString(),
                            registrationVM.registrationErrorMessage
                        )
                        registrationVM.userInteractor.addUserInfo(registrationVM.username)
                        if (!registrationVM.registrationErrorMessage.value.isNullOrEmpty()) {
                            Toast.makeText(
                                context, registrationVM.registrationErrorMessage.value,
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            activity?.startApp()
                            var toast = Toast.makeText(
                                context,
                                "Please, confirm your account at ${registrationVM.email.value}",
                                Toast.LENGTH_LONG
                            )
                            toast.view?.setBackgroundResource(R.color.divider_grey)
                            val tv = toast.view?.findViewById<TextView>(android.R.id.message)
                            tv?.setTextColor(Color.parseColor("#711547"))
                            toast.show()
                        }
                    } catch (throwable: Throwable) {
                        Log.d("find bug", throwable.message.toString())
                    }
                }
            }
        }

        return binding.root
    }


    fun createAccount() {
        registrationVM.onRegistrationClick()
        binding.tiSignUpEmail.error = registrationVM.emailError.value
        binding.tiSignUpUsername.error = registrationVM.usernameError.value
        binding.tiSignUpPassword.error = registrationVM.passwordError.value
    }

    private fun onLogin() {
        navigationController.navigate(R.id.action_registrationFragment_to_loginFragment)
    }

}