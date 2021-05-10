package com.example.technpractiseandroid.auth.registration

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class RegistrationVM @Inject constructor(
private val mAuth: FirebaseAuth,
private val db: FirebaseFirestore
): ViewModel() {
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val username = MutableLiveData("")
    var emailError = MutableLiveData("")
    var usernameError= MutableLiveData("")
    var passwordError= MutableLiveData("")
    var isHaveError = false


    fun onRegistrationClick() {
        mAuth.createUserWithEmailAndPassword(email.value.toString(), password.value.toString())
            .addOnCompleteListener(this as Activity) { task -> //fix
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("reg", "createUserWithEmail:success")
                    val user = mAuth.currentUser
                    user.sendEmailVerification()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d("reg", "createUserWithEmail:failure", task.exception)

                }

            }
    }


    fun validForm(): Boolean{
        clearErrors()
        isHaveError = false
        if (email.value.isNullOrEmpty()) {
            emailError.value = "Enter email"
            isHaveError = true
        } //add unique check
        if (username.value.isNullOrEmpty()) {
            usernameError.value = "Enter username"
            isHaveError = true

        }
        if (password.value.isNullOrEmpty()) {
            passwordError.value = "Enter password"
            isHaveError = true
        } else{
            if(password.value!!.length < 6){
                passwordError.value= "Password length less than 6 symbols"
                isHaveError = true
            }
        }
        return isHaveError
    }

    fun clearErrors(){
        emailError.value = ""
        usernameError.value = ""
        passwordError.value = ""
    }
}