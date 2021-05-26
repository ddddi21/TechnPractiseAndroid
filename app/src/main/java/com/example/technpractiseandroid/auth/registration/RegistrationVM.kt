package com.example.technpractiseandroid.auth.registration

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technpractiseandroid.interactors.UserInteractor
import com.example.technpractiseandroid.user.User
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.launch
import timber.log.Timber


class RegistrationVM @Inject constructor(
private val mAuth: FirebaseAuth,
var userInteractor: UserInteractor
): ViewModel() {
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val username = MutableLiveData("")
    var emailError = MutableLiveData("")
    var usernameError= MutableLiveData("")
    var passwordError= MutableLiveData("")
    var isHaveError = false
    var registrationErrorMessage = MutableLiveData("")

    fun onRegistrationClick(){
        validForm()
    }

    fun validForm(): Boolean{
        clearErrors()
        isHaveError = false
        if (email.value.isNullOrEmpty()) {
            emailError.value = "Enter email"
            isHaveError = true
        } else{
        if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()){
            emailError.value = "invalid email form"
            isHaveError = true
        }
        }
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
        registrationErrorMessage.value = ""
    }
}