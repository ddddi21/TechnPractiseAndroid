package com.example.technpractiseandroid.auth.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModelProvider
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.R
import com.example.technpractiseandroid.base.BaseFragment
import com.example.technpractiseandroid.base.navigationController
import com.example.technpractiseandroid.databinding.SignUpFragmentBinding
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.sign_up_fragment.view.*
import javax.inject.Inject

class RegistrationFragment: BaseFragment<RegistrationVM>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var mAuth: FirebaseAuth

    private var isHaveError = false
    private var registrationVM: RegistrationVM =
        ViewModelProvider(this, viewModelFactory).get(RegistrationVM::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        MyMainApplication().authComponent().inject(fragment = this@RegistrationFragment)
//        if(mAuth != null)
//        navigate to login
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = SignUpFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.vm = registrationVM

        val enterBtn = (view as ViewGroup).findViewById<Button>(R.id.btn_sign_up_enter)
        val toLogin = (view as ViewGroup).findViewById<TextView>(R.id.tv_sign_up_to_sign_in)

        toLogin.setOnClickListener {
            onLogin()
        }

        enterBtn.setOnClickListener {
           errorMessages(view as ViewGroup)
           if(!isHaveError){
               viewModel.onRegistrationClick()
           }
       }
        return binding.root
    }

    fun errorMessages(root:ViewGroup) {
        val email = root.findViewById<AppCompatEditText>(R.id.et_sign_up_email)
        val emailError = root.findViewById<TextInputLayout>(R.id.ti_sign_up_email)
        val username = root.findViewById<AppCompatEditText>(R.id.et_sign_up_username)
        val usernameError = root.findViewById<TextInputLayout>(R.id.ti_sign_up_username)
        val password = root.findViewById<AppCompatEditText>(R.id.et_sign_up_password)
        val passwordError = root.findViewById<TextInputLayout>(R.id.ti_sign_up_password)

        if (email.text?.isEmpty() == true) {
            emailError.error = "Enter email"
            isHaveError = true
        }
        if (username.text?.isEmpty() == true) {
            usernameError.error = "Enter username"
            isHaveError = true
        }
        if (password.text?.isEmpty() == true) {
            passwordError.error = "Enter password"
            isHaveError = true
        }
    }

    fun createAccount() {
//    mAuth.createUserWithEmailAndPassword()
    }

    private fun onLogin(){
        navigationController.navigate(R.id.action_registrationFragment_to_loginFragment)
    }

}