package com.example.technpractiseandroid.auth.login

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.google.firebase.auth.FirebaseAuth
import timber.log.Timber
import javax.inject.Inject


class LoginVM @Inject constructor(
        private val mAuth: FirebaseAuth
        ):ViewModel(){
        val email = MutableLiveData("")
        val password = MutableLiveData("")
        val emailError = MutableLiveData("")
        val passwordError = MutableLiveData("")

        var loginErrorMessage = ""


//если неверный пароль тоже не заходит в блок else
        fun onLoginClick(activity: Activity){
                mAuth.signInWithEmailAndPassword(email.value.toString(), password.value.toString())
                        .addOnCompleteListener(activity) { task ->
                                if (task.isSuccessful) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Timber.d( "signInWithEmail:success")
                                } else {
                                        // If sign in fails, display a message to the user.
                                        Timber.d( task.exception,"signInWithEmail:failure")
                                        loginErrorMessage = task.exception?.message.toString()
                                }
                        }
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