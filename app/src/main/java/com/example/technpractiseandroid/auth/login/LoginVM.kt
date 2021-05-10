package com.example.technpractiseandroid.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class LoginVM @Inject constructor(

):ViewModel(){
        val email = MutableLiveData("")
        val password = MutableLiveData("")
        val emailError = MutableLiveData("")
        val passwordError = MutableLiveData("")


        fun onLoginClick(){

        }

        fun validForm(): Boolean{
                clearErrors()
                var isHaveError = false
                if (email.value.isNullOrEmpty()) {
                        emailError.value = "Enter email"
                        isHaveError = true
                } //add unique check
                if (password.value.isNullOrEmpty()) {
                        passwordError.value = "Enter password"
                        isHaveError = true
                } else{
                        if(password.value!!.length < 6){
                                passwordError.value = "Password length less than 6 symbols"
                                isHaveError = true
                        }
                }
                return isHaveError
        }

        fun clearErrors(){
                emailError.value = ""
                passwordError.value = ""
        }

}