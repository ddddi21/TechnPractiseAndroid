package com.example.technpractiseandroid.auth.registration

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModelProvider
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.R
import com.example.technpractiseandroid.auth.login.LoginVM
import com.example.technpractiseandroid.base.BaseFragment
import com.example.technpractiseandroid.base.MainActivity
import com.example.technpractiseandroid.base.navigationController
import com.example.technpractiseandroid.base.startApp
import com.example.technpractiseandroid.databinding.SignUpFragmentBinding
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.sign_up_fragment.view.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class RegistrationFragment: BaseFragment<RegistrationVM>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var mAuth: FirebaseAuth

    private var isHaveError = false
    lateinit var registrationVM: RegistrationVM

    lateinit var binding: SignUpFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        MyMainApplication.appComponent.inject(fragment = this@RegistrationFragment)
        registrationVM = ViewModelProvider(this, viewModelFactory).get(RegistrationVM::class.java)

//        if(mAuth != null)
//        navigate to login
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SignUpFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.vm = registrationVM

        binding.tvSignUpToSignIn.setOnClickListener {
            onLogin()
        }

        binding.btnSignUpEnter.setOnClickListener {
            createAccount()
            val nevalid = registrationVM.validForm()
            if (nevalid){
                return@setOnClickListener
            } else {
                if(!registrationVM.registrationErrorMessage.isEmpty()){
                    Toast.makeText(context, registrationVM.registrationErrorMessage, Toast.LENGTH_SHORT).show()
                } else{
                    activity?.startApp()
                    Toast.makeText(context, "Please, confirm your account at ${registrationVM.email.value}", Toast.LENGTH_SHORT).show()
                }
            }
       }

        return binding.root
    }


    fun createAccount()= runBlocking {
        launch {
            registrationVM.onRegistrationClick()
            binding.tiSignUpEmail.error = registrationVM.emailError.value
            binding.tiSignUpUsername.error = registrationVM.usernameError.value
            binding.tiSignUpPassword.error = registrationVM.passwordError.value
        }
    }

    private fun onLogin(){
        navigationController.navigate(R.id.action_registrationFragment_to_loginFragment)
    }

}